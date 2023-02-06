/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2023 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.types.MailStopNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.MailStopNumberAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;
import org.xmlobjects.xal.model.types.PostalDeliveryPointType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class MailStopAdapter extends AddressObjectAdapter<PostalDeliveryPoint> {

    @Override
    public PostalDeliveryPoint createObject(QName name, Object parent) throws ObjectBuildException {
        PostalDeliveryPoint object = new PostalDeliveryPoint(PostalDeliveryPointType.MAIL_STOP);
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(PostalDeliveryPoint object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> object.getOtherAttributes().add("Type", v));
    }

    @Override
    public void buildChildObject(PostalDeliveryPoint object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "MailStopName":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(MailStopNameAdapter.class));
                    break;
                case "MailStopNumber":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(MailStopNumberAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, PostalDeliveryPoint object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(PostalDeliveryPoint object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Identifier name = null;
        Identifier number = null;

        if (object.isSetIdentifiers()) {
            for (Identifier identifier : object.getIdentifiers()) {
                if (name == null && identifier.getType() != IdentifierElementType.NUMBER)
                    name = identifier;
                else if (number == null && identifier.getType() == IdentifierElementType.NUMBER)
                    number = identifier;
            }
        }

        if (name != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStopName"), name, MailStopNameAdapter.class, namespaces);

        if (number != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStopNumber"), number, MailStopNumberAdapter.class, namespaces);
    }
}
