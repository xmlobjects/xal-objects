/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2022 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.model.Child;
import org.xmlobjects.model.ChildList;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.*;
import org.xmlobjects.xal.adapter.deprecated.helper.NumberRange;
import org.xmlobjects.xal.adapter.deprecated.helper.ParsedNumber;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;
import org.xmlobjects.xal.model.types.ThoroughfareNameOrNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareNumberRangeAdapter implements ObjectBuilder<ChildList<ThoroughfareNameOrNumber>>, ObjectSerializer<NumberRange> {
    private Identifier separator;

    @Override
    public ChildList<ThoroughfareNameOrNumber> createObject(QName name, Object parent) throws ObjectBuildException {
        ChildList<ThoroughfareNameOrNumber> object = new ChildList<>(parent instanceof Child ? (Child) parent : null);
        separator = null;
        return object;
    }

    @Override
    public void initializeObject(ChildList<ThoroughfareNameOrNumber> object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Separator").ifPresent(v -> separator = new Identifier(v, IdentifierElementType.SEPARATOR));
    }

    @Override
    public void buildChildObject(ChildList<ThoroughfareNameOrNumber> object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    buildAddressLine(object, reader);
                    break;
                case "ThoroughfareNumberFrom":
                    buildNumber(object, IdentifierElementType.RANGE_FROM, reader);
                    break;
                case "ThoroughfareNumberTo":
                    if (separator != null) {
                        object.add(new ThoroughfareNameOrNumber(separator));
                        separator = null;
                    }

                    buildNumber(object, IdentifierElementType.RANGE_TO, reader);
                    break;
            }
        }
    }

    private void buildNumber(ChildList<ThoroughfareNameOrNumber> object, IdentifierElementType type, XMLReader reader) throws ObjectBuildException, XMLReadException {
        int depth = reader.getDepth();
        while (reader.hasNext() && reader.getDepth() >= depth && reader.nextTag() == EventType.START_ELEMENT) {
            QName name = reader.getName();
            if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
                switch (name.getLocalPart()) {
                    case "AddressLine":
                        buildAddressLine(object, reader);
                        break;
                    case "ThoroughfareNumberPrefix":
                        object.add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNumberPrefixAdapter.class)));
                        break;
                    case "ThoroughfareNumber":
                        Identifier number = reader.getObjectUsingBuilder(ThoroughfareNumberAdapter.class);
                        number.setType(type);
                        object.add(new ThoroughfareNameOrNumber(number));
                        break;
                    case "ThoroughfareNumberSuffix":
                        object.add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNumberSuffixAdapter.class)));
                        break;
                }
            }
        }
    }

    private void buildAddressLine(ChildList<ThoroughfareNameOrNumber> object, XMLReader reader) throws ObjectBuildException, XMLReadException {
        Child parent = object.getParent();
        if (parent != null) {
            Address address = parent.getParent(Address.class);
            if (address != null) {
                if (address.getFreeTextAddress() == null)
                    address.setFreeTextAddress(new FreeTextAddress());

                address.getFreeTextAddress().getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
            }
        }
    }

    @Override
    public void initializeElement(Element element, NumberRange object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.getSeparator() != null)
            element.addAttribute("Separator", object.getSeparator().getContent());
    }

    @Override
    public void writeChildElements(NumberRange object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        writeRangeNumber(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberFrom"), object.getRangeFrom(), namespaces, writer);
        writeRangeNumber(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberTo"), object.getRangeTo(), namespaces, writer);
    }

    private void writeRangeNumber(Element element, ParsedNumber rangeNumber, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        writer.writeStartElement(element);

        if (rangeNumber != null) {
            for (Identifier prefix : rangeNumber.getPrefixes())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberPrefix"), prefix, ThoroughfareNumberPrefixAdapter.class, namespaces);

            for (Identifier number : rangeNumber.getNumbers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumber"), number, ThoroughfareNumberAdapter.class, namespaces);

            for (Identifier suffix : rangeNumber.getSuffixes())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberSuffix"), suffix, ThoroughfareNumberSuffixAdapter.class, namespaces);
        }

        writer.writeEndElement();
    }
}
