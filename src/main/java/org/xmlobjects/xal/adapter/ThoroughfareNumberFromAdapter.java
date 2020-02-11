/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.ThoroughfareNumberContent;
import org.xmlobjects.xal.model.ThoroughfareNumberFrom;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareNumberFromAdapter implements ObjectBuilder<ThoroughfareNumberFrom>, ObjectSerializer<ThoroughfareNumberFrom> {

    @Override
    public ThoroughfareNumberFrom createObject(QName name) throws ObjectBuildException {
        return new ThoroughfareNumberFrom();
    }

    @Override
    public void initializeObject(ThoroughfareNumberFrom object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(v -> object.getContents().add(new ThoroughfareNumberContent(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(ThoroughfareNumberFrom object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(AddressLineAdapter.class)));
                    break;
                case "ThoroughfareNumberPrefix":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(ThoroughfareNumberPrefixAdapter.class)));
                    break;
                case "ThoroughfareNumber":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(ThoroughfareNumberAdapter.class)));
                    break;
                case "ThoroughfareNumberSuffix":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(ThoroughfareNumberSuffixAdapter.class)));
                    break;
            }
        }

        reader.getTextContent().ifPresent(v -> object.getContents().add(new ThoroughfareNumberContent(v)));
    }

    @Override
    public Element createElement(ThoroughfareNumberFrom object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberFrom");
    }

    @Override
    public void initializeElement(Element element, ThoroughfareNumberFrom object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Code", object.getCode());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(ThoroughfareNumberFrom object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (ThoroughfareNumberContent content : object.getContents()) {
            if (content.isSetString())
                writer.writeCharacters(content.getString());
            else if (content.isSetAddressLine())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), content.getAddressLine(), AddressLineAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumberPrefix())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberPrefix"), content.getThoroughfareNumberPrefix(), ThoroughfareNumberPrefixAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumber())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumber"), content.getThoroughfareNumber(), ThoroughfareNumberAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumberSuffix())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberSuffix"), content.getThoroughfareNumberSuffix(), ThoroughfareNumberSuffixAdapter.class, namespaces);
        }
    }
}
