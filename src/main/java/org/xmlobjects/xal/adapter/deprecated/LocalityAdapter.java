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
import org.xmlobjects.xal.adapter.deprecated.types.LocalityNameAdapter;
import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfLocality;
import org.xmlobjects.xal.model.types.LocalityName;
import org.xmlobjects.xal.model.types.LocalityType;
import org.xmlobjects.xal.model.types.PostalDeliveryPointType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class LocalityAdapter extends AddressObjectAdapter<Locality> {

    @Override
    public Locality createObject(QName name, Object parent) throws ObjectBuildException {
        Locality object = new Locality();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Locality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("UsageType").ifPresent(v -> object.getOtherAttributes().add("UsageType", v));
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
        attributes.getValue("Type").ifPresent(v -> {
            LocalityType type = LocalityType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("Type", v);
        });
    }

    @Override
    public void buildChildObject(Locality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "LocalityName":
                    object.getNameElements().add(reader.getObjectUsingBuilder(LocalityNameAdapter.class));
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
                    object.setSubLocality(reader.getObjectUsingBuilder(DependentLocalityAdapter.class));
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
    public void initializeElement(Element element, Locality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("UsageType", object.getOtherAttributes().getValue("UsageType"));
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));

        if (object.getType() != null)
            element.addAttribute("Type", object.getType().toValue());
        else
            element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(Locality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Address address = object.getParent(Address.class);
        DeprecatedPropertiesOfLocality properties = object.hasDeprecatedProperties() ?
                object.getDeprecatedProperties() :
                null;

        if (object.isSetNameElements()) {
            for (LocalityName nameElement : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LocalityName"), nameElement, LocalityNameAdapter.class, namespaces);
        }

        PostalDeliveryPoint postBox = null;
        if (address != null && address.getPostalDeliveryPoint() != null && address.getPostalDeliveryPoint().getType() == PostalDeliveryPointType.PO_BOX) {
            postBox = address.getPostalDeliveryPoint();
        } else if (properties != null) {
            postBox = properties.getPostBox();
        }

        PostOffice postOffice = null;
        if (address != null && address.getPostOffice() != null) {
            postOffice = address.getPostOffice();
        } else if (properties != null) {
            postOffice = properties.getPostOffice();
        }

        if (postBox != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), postBox, PostBoxAdapter.class, namespaces);
        else if (postOffice != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOffice"), postOffice, PostOfficeAdapter.class, namespaces);
        else if (properties != null && properties.getLargeMailUser() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUser"), properties.getLargeMailUser(), LargeMailUserAdapter.class, namespaces);
        else if (properties != null && properties.getPostalRoute() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalRoute"), properties.getPostalRoute(), PostalRouteAdapter.class, namespaces);

        boolean hasDependentLocality = object.getSubLocality() != null;
        Thoroughfare thoroughfare = null;
        if (!hasDependentLocality && address != null && address.getThoroughfare() != null) {
            thoroughfare = address.getThoroughfare();
        } else if (properties != null) {
            thoroughfare = properties.getThoroughfare();
        }

        if (thoroughfare != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), thoroughfare, ThoroughfareAdapter.class, namespaces);

        Premises premise = null;
        if (!hasDependentLocality && address != null && address.getThoroughfare() == null && address.getPremises() != null) {
            premise = address.getPremises();
        } else if (properties != null) {
            premise = properties.getPremise();
        }

        if (premise != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise"), premise, PremiseAdapter.class, namespaces);

        if (hasDependentLocality)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentLocality"), object.getSubLocality(), DependentLocalityAdapter.class, namespaces);

        PostCode postalCode = null;
        if (address != null && address.getPostCode() != null) {
            postalCode = address.getPostCode();
        } else if (properties != null) {
            postalCode = properties.getPostalCode();
        }

        if (postalCode != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), postalCode, PostalCodeAdapter.class, namespaces);
    }
}
