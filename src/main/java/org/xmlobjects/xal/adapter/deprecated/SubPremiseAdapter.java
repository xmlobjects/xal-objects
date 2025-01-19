/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2025 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.helper.PremiseNamesAndNumbers;
import org.xmlobjects.xal.adapter.deprecated.types.*;
import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubPremises;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.PremisesName;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;
import org.xmlobjects.xal.model.types.SubPremisesType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseAdapter extends AddressObjectAdapter<SubPremises> {

    @Override
    public SubPremises createObject(QName name, Object parent) throws ObjectBuildException {
        SubPremises object = new SubPremises();
        if (parent instanceof Child child)
            object.setParent(child);

        return object;
    }

    @Override
    public void initializeObject(SubPremises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> {
            SubPremisesType type = SubPremisesType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("Type", v);
        });
    }

    @Override
    public void buildChildObject(SubPremises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "SubPremiseName":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(SubPremiseNameAdapter.class)));
                    break;
                case "SubPremiseLocation":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(SubPremiseLocationAdapter.class)));
                    break;
                case "SubPremiseNumber":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(SubPremiseNumberAdapter.class)));
                    break;
                case "SubPremiseNumberPrefix":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(SubPremiseNumberPrefixAdapter.class)));
                    break;
                case "SubPremiseNumberSuffix":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(SubPremiseNumberSuffixAdapter.class)));
                    break;
                case "BuildingName":
                    object.getDeprecatedProperties().getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
                    break;
                case "Firm":
                    object.getDeprecatedProperties().setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
                    break;
                case "MailStop":
                    PostalDeliveryPoint mailStop = reader.getObjectUsingBuilder(MailStopAdapter.class);
                    if (address != null && address.getPostalDeliveryPoint() == null)
                        address.setPostalDeliveryPoint(mailStop);
                    else
                        object.getDeprecatedProperties().setMailStop(mailStop);
                    break;
                case "PostalCode":
                    PostCode postalCode = reader.getObjectUsingBuilder(PostalCodeAdapter.class);
                    if (address != null && address.getPostCode() == null)
                        address.setPostCode(postalCode);
                    else
                        object.getDeprecatedProperties().setPostalCode(postalCode);
                    break;
                case "SubPremise":
                    object.getDeprecatedProperties().setSubPremise(reader.getObjectUsingBuilder(SubPremiseAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, SubPremises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);

        if (object.getType() != null)
            element.addAttribute("Type", object.getType().toValue());
        else
            element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(SubPremises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        PremiseNamesAndNumbers namesAndNumbers = PremiseNamesAndNumbers.of(object);

        for (PremisesName name : namesAndNumbers.getNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseName"), name, SubPremiseNameAdapter.class, namespaces);

        if (namesAndNumbers.getPremiseLocation() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseLocation"), namesAndNumbers.getPremiseLocation(), SubPremiseLocationAdapter.class, namespaces);
        else {
            for (Identifier number : namesAndNumbers.getNumbers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumber"), number, SubPremiseNumberAdapter.class, namespaces);
        }

        for (Identifier prefix : namesAndNumbers.getPrefixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumberPrefix"), prefix, SubPremiseNumberPrefixAdapter.class, namespaces);

        for (Identifier suffix : namesAndNumbers.getSuffixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumberSuffix"), suffix, SubPremiseNumberSuffixAdapter.class, namespaces);

        if (object.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfSubPremises properties = object.getDeprecatedProperties();

            if (properties.isSetBuildingNames()) {
                for (Identifier buildingName : properties.getBuildingNames())
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "BuildingName"), buildingName, BuildingNameAdapter.class, namespaces);
            }

            if (properties.getFirm() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), properties.getFirm(), FirmAdapter.class, namespaces);

            if (properties.getMailStop() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStop"), properties.getMailStop(), MailStopAdapter.class, namespaces);

            if (properties.getPostalCode() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), properties.getPostalCode(), PostalCodeAdapter.class, namespaces);
        }
    }
}
