/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2021 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.types.*;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfPostalDeliveryPoint;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;
import org.xmlobjects.xal.model.types.PostalDeliveryPointType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostBoxAdapter extends AddressObjectAdapter<PostalDeliveryPoint> {

    @Override
    public PostalDeliveryPoint createObject(QName name, Object parent) throws ObjectBuildException {
        PostalDeliveryPoint object = new PostalDeliveryPoint(PostalDeliveryPointType.PO_BOX);
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(PostalDeliveryPoint object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> object.getOtherAttributes().add("Type", v));
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
    }

    @Override
    public void buildChildObject(PostalDeliveryPoint object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "PostBoxNumber":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(PostBoxNumberAdapter.class));
                    break;
                case "PostBoxNumberPrefix":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(PostBoxNumberPrefixAdapter.class));
                    break;
                case "PostBoxNumberSuffix":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(PostBoxNumberSuffixAdapter.class));
                    break;
                case "PostBoxNumberExtension":
                    object.getIdentifiers().add(reader.getObjectUsingBuilder(PostBoxNumberExtensionAdapter.class));
                    break;
                case "Firm":
                    object.getDeprecatedProperties().setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
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
    public void initializeElement(Element element, PostalDeliveryPoint object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));
    }

    @Override
    public void writeChildElements(PostalDeliveryPoint object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Identifier number = null;
        Identifier prefix = null;
        Identifier suffix = null;
        Identifier extension = null;

        if (object.isSetIdentifiers()) {
            for (Identifier identifier : object.getIdentifiers()) {
                IdentifierElementType type = identifier.getType();
                switch (type != null ? type : IdentifierElementType.NUMBER) {
                    case PREFIX:
                        if (prefix == null)
                            prefix = identifier;
                        break;
                    case SUFFIX:
                        if (suffix == null)
                            suffix = identifier;
                        break;
                    case EXTENSION:
                        if (extension == null)
                            extension = identifier;
                        break;
                    default:
                        if (number == null)
                            number = identifier;
                        break;
                }
            }
        }

        if (number != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumber"), number, PostBoxNumberAdapter.class, namespaces);

        if (prefix != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberPrefix"), prefix, PostBoxNumberPrefixAdapter.class, namespaces);

        if (suffix != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberSuffix"), suffix, PostBoxNumberSuffixAdapter.class, namespaces);

        if (extension != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberExtension"), extension, PostBoxNumberExtensionAdapter.class, namespaces);

        if (object.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPostalDeliveryPoint properties = object.getDeprecatedProperties();

            if (properties.getFirm() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), properties.getFirm(), FirmAdapter.class, namespaces);

            if (properties.getPostalCode() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), properties.getPostalCode(), PostalCodeAdapter.class, namespaces);
        }
    }
}
