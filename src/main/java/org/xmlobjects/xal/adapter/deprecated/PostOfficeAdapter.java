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
import org.xmlobjects.xal.adapter.deprecated.types.PostOfficeNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PostOfficeNumberAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostOffice;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;
import org.xmlobjects.xal.model.types.PostalDeliveryPointType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostOfficeAdapter extends AddressObjectAdapter<PostOffice> {

    @Override
    public PostOffice createObject(QName name, Object parent) throws ObjectBuildException {
        PostOffice object = new PostOffice();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(PostOffice object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
    }

    @Override
    public void buildChildObject(PostOffice object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            Address address = object.getParent(Address.class);
            switch (name.getLocalPart()) {
                case "AddressLine":
                    if (address != null) {
                        if (address.getFreeTextAddress() == null)
                            address.setFreeTextAddress(new FreeTextAddress());

                        address.getFreeTextAddress().getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    }
                    break;
                case "PostOfficeName":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(PostOfficeNameAdapter.class));
                    break;
                case "PostOfficeNumber":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(PostOfficeNumberAdapter.class));
                    break;
                case "PostalRoute":
                    object.getDeprecatedProperties().setPostalRoute(reader.getObjectUsingBuilder(PostalRouteAdapter.class));
                    break;
                case "PostBox":
                    PostalDeliveryPoint postBox = reader.getObjectUsingBuilder(PostBoxAdapter.class);
                    if (address != null && address.getPostalDeliveryPoint() == null)
                        address.setPostalDeliveryPoint(postBox);
                    else
                        object.getDeprecatedProperties().setPostBox(postBox);
                    break;
                case "PostalCode":
                    PostCode postalCode = reader.getObjectUsingBuilder(PostalCodeAdapter.class);
                    if (address != null && address.getPostCode() == null)
                        address.setPostCode(postalCode);
                    else
                        object.getDeprecatedProperties().setPostalCode(postalCode);
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, PostOffice object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getType());
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));
    }

    @Override
    public void writeChildElements(PostOffice object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Address address = object.getParent(Address.class);

        Identifier number = null;
        boolean hasNames = false;

        for (Identifier identifier : object.getIdentifiers()) {
            if (identifier.getType() == IdentifierElementType.NAME) {
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOfficeName"), identifier, PostOfficeNameAdapter.class, namespaces);
                hasNames = true;
            } else if (number == null && identifier.getType() == IdentifierElementType.NUMBER)
                number = identifier;
        }

        if (!hasNames && number != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOfficeNumber"), number, PostOfficeNumberAdapter.class, namespaces);

        if (object.getDeprecatedProperties().getPostalRoute() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalRoute"), object.getDeprecatedProperties().getPostalRoute(), PostalRouteAdapter.class, namespaces);

        PostalDeliveryPoint postBox = address != null && address.getLocality() == null && address.getPostalDeliveryPoint() != null && address.getPostalDeliveryPoint().getType() == PostalDeliveryPointType.PO_BOX ?
                address.getPostalDeliveryPoint() :
                object.getDeprecatedProperties().getPostBox();

        if (postBox != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), postBox, PostBoxAdapter.class, namespaces);

        PostCode postalCode = address != null && address.getLocality() == null && address.getPostCode() != null ?
                address.getPostCode() :
                object.getDeprecatedProperties().getPostalCode();

        if (postalCode != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), postalCode, PostalCodeAdapter.class, namespaces);
    }
}
