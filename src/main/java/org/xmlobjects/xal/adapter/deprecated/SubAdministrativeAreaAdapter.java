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
import org.xmlobjects.xal.adapter.deprecated.types.SubAdministrativeAreaNameAdapter;
import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubAdministrativeArea;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaName;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubAdministrativeAreaAdapter extends AddressObjectAdapter<SubAdministrativeArea> {

    @Override
    public SubAdministrativeArea createObject(QName name, Object parent) throws ObjectBuildException {
        SubAdministrativeArea object = new SubAdministrativeArea();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(SubAdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("UsageType").ifPresent(v -> object.getOtherAttributes().add("UsageType", v));
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
        attributes.getValue("Type").ifPresent(v -> {
            SubAdministrativeAreaType type = SubAdministrativeAreaType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("Type", v);
        });
    }

    @Override
    public void buildChildObject(SubAdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "SubAdministrativeAreaName":
                    object.getNameElements().add(reader.getObjectUsingBuilder(SubAdministrativeAreaNameAdapter.class));
                    break;
                case "Locality":
                    Locality locality = reader.getObjectUsingBuilder(LocalityAdapter.class);
                    if (address != null && address.getLocality() == null)
                        address.setLocality(locality);
                    else
                        object.getDeprecatedProperties().setLocality(locality);
                    break;
                case "PostOffice":
                    PostOffice postOffice = reader.getObjectUsingBuilder(PostOfficeAdapter.class);
                    if (address != null && address.getPostOffice() == null)
                        address.setPostOffice(postOffice);
                    else
                        object.getDeprecatedProperties().setPostOffice(postOffice);
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
    public void initializeElement(Element element, SubAdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("UsageType", object.getOtherAttributes().getValue("UsageType"));
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));

        if (object.getType() != null)
            element.addAttribute("Type", object.getType().toValue());
        else
            element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(SubAdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Address address = object.getParent(Address.class);
        DeprecatedPropertiesOfSubAdministrativeArea properties = object.hasDeprecatedProperties() ?
                object.getDeprecatedProperties() :
                null;

        if (object.isSetNameElements()) {
            for (SubAdministrativeAreaName nameElement : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubAdministrativeAreaName"), nameElement, SubAdministrativeAreaNameAdapter.class, namespaces);
        }

        Locality locality = null;
        if (address != null && address.getLocality() != null) {
            locality = address.getLocality();
        } else if (properties != null) {
            locality = properties.getLocality();
        }

        PostOffice postOffice = null;
        if (address != null && address.getPostOffice() != null) {
            postOffice = address.getPostOffice();
        } else if (properties != null) {
            postOffice = properties.getPostOffice();
        }

        PostCode postalCode = null;
        if (address != null && address.getPostCode() != null) {
            postalCode = address.getPostCode();
        } else if (properties != null) {
            postalCode = properties.getPostalCode();
        }

        if (locality != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), locality, LocalityAdapter.class, namespaces);
        else if (postOffice != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOffice"), postOffice, PostOfficeAdapter.class, namespaces);
        else if (postalCode != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), postalCode, PostalCodeAdapter.class, namespaces);
    }
}
