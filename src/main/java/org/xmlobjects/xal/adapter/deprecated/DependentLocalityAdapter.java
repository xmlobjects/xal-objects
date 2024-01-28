/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2024 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.types.DependentLocalityNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.DependentLocalityNumberAdapter;
import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubLocality;
import org.xmlobjects.xal.model.types.SubLocalityName;
import org.xmlobjects.xal.model.types.SubLocalityNameType;
import org.xmlobjects.xal.model.types.SubLocalityType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class DependentLocalityAdapter extends AddressObjectAdapter<SubLocality> {

    @Override
    public SubLocality createObject(QName name, Object parent) throws ObjectBuildException {
        SubLocality object = new SubLocality();
        if (parent instanceof Child child)
            object.setParent(child);

        return object;
    }

    @Override
    public void initializeObject(SubLocality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("UsageType").ifPresent(v -> object.getOtherAttributes().add("UsageType", v));
        attributes.getValue("Connector").ifPresent(v -> object.getOtherAttributes().add("Connector", v));
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
        attributes.getValue("Type").ifPresent(v -> {
            SubLocalityType type = SubLocalityType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("Type", v);
        });
    }

    @Override
    public void buildChildObject(SubLocality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "DependentLocalityName":
                    object.getNameElements().add(reader.getObjectUsingBuilder(DependentLocalityNameAdapter.class));
                    break;
                case "DependentLocalityNumber":
                    object.getNameElements().add(reader.getObjectUsingBuilder(DependentLocalityNumberAdapter.class));
                    break;
                case "PostBox":
                    PostalDeliveryPoint postBox = reader.getObjectUsingBuilder(PostBoxAdapter.class);
                    if (address != null && address.getPostalDeliveryPoint() == null)
                        address.setPostalDeliveryPoint(postBox);
                    else
                        object.getDeprecatedProperties().setPostBox(postBox);
                    break;
                case "LargeMailUser":
                    Premises largeMailUser = reader.getObjectUsingBuilder(LargeMailUserAdapter.class);
                    if (address != null && address.getPremises() == null)
                        address.setPremises(largeMailUser);
                    else
                        object.getDeprecatedProperties().setLargeMailUser(largeMailUser);
                    break;
                case "PostOffice":
                    PostOffice postOffice = reader.getObjectUsingBuilder(PostOfficeAdapter.class);
                    if (address != null && address.getPostOffice() == null)
                        address.setPostOffice(postOffice);
                    else
                        object.getDeprecatedProperties().setPostOffice(postOffice);
                    break;
                case "PostalRoute":
                    object.getDeprecatedProperties().setPostalRoute(reader.getObjectUsingBuilder(PostalRouteAdapter.class));
                    break;
                case "Thoroughfare":
                    Thoroughfare thoroughfare = reader.getObjectUsingBuilder(ThoroughfareAdapter.class);
                    if (address != null && address.getThoroughfare() == null)
                        address.setThoroughfare(thoroughfare);
                    else
                        object.getDeprecatedProperties().setThoroughfare(thoroughfare);
                    break;
                case "Premise":
                    Premises premise = reader.getObjectUsingBuilder(PremiseAdapter.class);
                    if (address != null && address.getPremises() == null)
                        address.setPremises(premise);
                    else
                        object.getDeprecatedProperties().setPremise(premise);
                    break;
                case "DependentLocality":
                    object.getDeprecatedProperties().setDependentLocality(reader.getObjectUsingBuilder(DependentLocalityAdapter.class));
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
    public void initializeElement(Element element, SubLocality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
        element.addAttribute("UsageType", object.getOtherAttributes().getValue("UsageType"));
        element.addAttribute("Connector", object.getOtherAttributes().getValue("Connector"));
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));
    }

    @Override
    public void writeChildElements(SubLocality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Address address = object.getParent(Address.class);
        SubLocalityName number = null;
        DeprecatedPropertiesOfSubLocality properties = object.hasDeprecatedProperties() ?
                object.getDeprecatedProperties() :
                null;

        if (object.isSetNameElements()) {
            for (SubLocalityName nameElement : object.getNameElements()) {
                if (nameElement.getNameType() == SubLocalityNameType.NAME)
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentLocalityName"), nameElement, DependentLocalityNameAdapter.class, namespaces);
                else if (number == null && nameElement.getNameType() == SubLocalityNameType.NUMBER)
                    number = nameElement;
            }
        }

        if (number != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentLocalityNumber"), number, DependentLocalityNumberAdapter.class, namespaces);

        if (properties != null) {
            if (properties.getPostBox() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), properties.getPostBox(), PostBoxAdapter.class, namespaces);
            else if (properties.getLargeMailUser() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUser"), properties.getLargeMailUser(), LargeMailUserAdapter.class, namespaces);
            else if (properties.getPostOffice() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOffice"), properties.getPostOffice(), PostOfficeAdapter.class, namespaces);
            else if (properties.getPostalRoute() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalRoute"), properties.getPostalRoute(), PostalRouteAdapter.class, namespaces);
        }

        boolean hasDependentLocality = properties != null && properties.getDependentLocality() != null;
        boolean hasParentThoroughfare = object.getParent(AbstractThoroughfare.class) != null;

        Thoroughfare thoroughfare = null;
        if (!hasDependentLocality && !hasParentThoroughfare && address != null && address.getThoroughfare() != null) {
            thoroughfare = address.getThoroughfare();
        } else if (properties != null) {
            thoroughfare = properties.getThoroughfare();
        }

        if (thoroughfare != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), thoroughfare, ThoroughfareAdapter.class, namespaces);

        Premises premise = null;
        if (!hasDependentLocality && address != null && (address.getThoroughfare() == null || hasParentThoroughfare) && address.getPremises() != null) {
            premise = address.getPremises();
        } else if (properties != null) {
            premise = properties.getPremise();
        }

        if (premise != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise"), premise, PremiseAdapter.class, namespaces);

        if (properties != null) {
            if (properties.getDependentLocality() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentLocality"), properties.getDependentLocality(), DependentLocalityAdapter.class, namespaces);

            if (properties.getPostalCode() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), properties.getPostalCode(), PostalCodeAdapter.class, namespaces);
        }
    }
}
