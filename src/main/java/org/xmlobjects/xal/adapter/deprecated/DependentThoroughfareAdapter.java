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

package org.xmlobjects.xal.adapter.deprecated;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.model.Child;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNumberAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfarePostDirectionAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfarePreDirectionAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.SubThoroughfare;
import org.xmlobjects.xal.model.types.ThoroughfareNameOrNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class DependentThoroughfareAdapter extends AddressObjectAdapter<SubThoroughfare> {

    @Override
    public SubThoroughfare createObject(QName name, Object parent) throws ObjectBuildException {
        SubThoroughfare object = new SubThoroughfare();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(SubThoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(object::setType);
    }

    @Override
    public void buildChildObject(SubThoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    Address address = object.getParent(Address.class);
                    if (address != null) {
                        if (address.getFreeTextAddress() == null)
                            address.setFreeTextAddress(new FreeTextAddress());

                        address.getFreeTextAddress().getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    }
                    break;
                case "ThoroughfarePreDirection":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfarePreDirectionAdapter.class)));
                    break;
                case "ThoroughfarePostDirection":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfarePostDirectionAdapter.class)));
                    break;
                case "ThoroughfareLeadingType":
                case "ThoroughfareTrailingType":
                case "ThoroughfareName":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNameAdapter.class)));
                    break;
            }
        }
    }

    @Override
    public void writeChildElements(SubThoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

        for (ThoroughfareNameOrNumber nameElementOrNumber : object.getNameElementOrNumber()) {
            if (nameElementOrNumber.isSetNameElement())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareName"), nameElementOrNumber.getNumber(), ThoroughfareNumberAdapter.class, namespaces);
        }
    }
}
