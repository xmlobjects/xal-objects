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

import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.AddressObject;
import org.xmlobjects.xal.model.types.AddressLine;
import org.xmlobjects.xal.model.types.AddressType;
import org.xmlobjects.xal.model.types.AddressUsage;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

@XMLElement(name = "AddressDetails", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class AddressDetailsAdapter extends AddressObjectAdapter<Address> {

    @Override
    public Address createObject(QName name, Object parent) throws ObjectBuildException {
        return new Address();
    }

    @Override
    public void initializeObject(Address object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("CurrentStatus").ifPresent(object::setStatus);
        attributes.getValue("AddressDetailsKey").ifPresent(object::setAddressKey);
        attributes.getValue("Code").ifPresent(v -> object.getOtherAttributes().add("Code", v));
        attributes.getValue("AddressType").ifPresent(v -> {
            AddressType type = AddressType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("AddressType", v);
        });
        attributes.getValue("Usage").ifPresent(v -> {
            AddressUsage usage = AddressUsage.fromValue(v);
            if (usage != null)
                object.setUsage(usage);
            else
                object.getOtherAttributes().add("Usage", v);
        });

        TextContent validFromDate = attributes.getValue("ValidFromDate");
        if (validFromDate.isDateTime())
            object.setDateValidFrom(validFromDate.getAsDateTime());
        else if (validFromDate.isPresent())
            object.getOtherAttributes().add("ValidFromDate", validFromDate.get());

        TextContent validToDate = attributes.getValue("ValidToDate");
        if (validToDate.isDateTime())
            object.setDateValidFrom(validToDate.getAsDateTime());
        else if (validToDate.isPresent())
            object.getOtherAttributes().add("ValidToDate", validToDate.get());
    }

    @Override
    public void buildChildObject(Address object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "Address":
                    object.setFreeTextAddress(reader.getObjectUsingBuilder(AddressAdapter.class));
                    break;
                case "AddressLines":
                    object.setFreeTextAddress(reader.getObjectUsingBuilder(AddressLinesAdapter.class));
                    break;
                case "Country":
                    object.setCountry(reader.getObjectUsingBuilder(CountryAdapter.class));
                    break;
                case "AdministrativeArea":
                    object.setAdministrativeArea(reader.getObjectUsingBuilder(AdministrativeAreaAdapter.class));
                    break;
                case "Locality":
                    object.setLocality(reader.getObjectUsingBuilder(LocalityAdapter.class));
                    break;
                case "Thoroughfare":
                    object.setThoroughfare(reader.getObjectUsingBuilder(ThoroughfareAdapter.class));
                    break;
                case "PostalServiceElements":
                    object.getDeprecatedProperties().setPostalServiceElements(reader.getObjectUsingBuilder(PostalServiceElementsAdapter.class));
                    break;
            }
        }
    }

    @Override
    public Element createElement(Address object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressDetails");
    }

    @Override
    public void initializeElement(Element element, Address object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("CurrentStatus", object.getStatus());
        element.addAttribute("AddressDetailsKey", object.getAddressKey());
        element.addAttribute("Code", object.getOtherAttributes().getValue("Code"));

        if (object.getType() != null)
            element.addAttribute("AddressType", object.getType().toValue());
        else
            element.addAttribute("AddressType", object.getOtherAttributes().getValue("AddressType"));

        if (object.getUsage() != null)
            element.addAttribute("Usage", object.getUsage().toValue());
        else
            element.addAttribute("Usage", object.getOtherAttributes().getValue("Usage"));

        if (object.getDateValidFrom() != null)
            element.addAttribute("ValidFromDate", TextContent.ofDateTime(object.getDateValidFrom()));
        else
            element.addAttribute("ValidFromDate", object.getOtherAttributes().getValue("ValidFromDate"));

        if (object.getDateValidTo() != null)
            element.addAttribute("ValidToDate", TextContent.ofDateTime(object.getDateValidTo()));
        else
            element.addAttribute("ValidToDate", object.getOtherAttributes().getValue("ValidToDate"));
    }

    @Override
    public void writeChildElements(Address object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.hasDeprecatedProperties() && object.getDeprecatedProperties().getPostalServiceElements() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalServiceElements"), object.getDeprecatedProperties().getPostalServiceElements(), PostalServiceElementsAdapter.class, namespaces);

        if (object.getCountry() != null)
            writeAddressObject(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Country"), object.getCountry(), CountryAdapter.class, object, namespaces, writer);
        else if (object.getAdministrativeArea() != null)
            writeAddressObject(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeArea"), object.getAdministrativeArea(), AdministrativeAreaAdapter.class, object, namespaces, writer);
        else if (object.getLocality() != null)
            writeAddressObject(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), object.getLocality(), LocalityAdapter.class, object, namespaces, writer);
        else if (object.getThoroughfare() != null)
            writeAddressObject(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), object.getThoroughfare(), ThoroughfareAdapter.class, object, namespaces, writer);
        else if (object.getFreeTextAddress() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLines"), object.getFreeTextAddress(), AddressLinesAdapter.class, namespaces);
    }

    private <T extends AddressObject> void writeAddressObject(Element element, T object, Class<? extends ObjectSerializer<T>> type, Address address, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        ObjectSerializer<T> serializer = writer.getOrCreateSerializer(type);
        serializer.initializeElement(element, object, namespaces, writer);
        writer.writeStartElement(element);

        if (address.getFreeTextAddress() != null && address.getFreeTextAddress().isSetAddressLines()) {
            for (AddressLine addressLine : address.getFreeTextAddress().getAddressLines())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);
        }

        writer.writeObjectUsingSerializer(object, serializer, namespaces);
        writer.writeEndElement();
    }
}
