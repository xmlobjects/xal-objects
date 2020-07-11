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
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.helper.PremiseNamesAndNumbers;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.BuildingNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseLocationAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberPrefixAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberRangeAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberSuffixAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.SubPremiseNameAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.SubPremises;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.PostalDeliveryPointType;
import org.xmlobjects.xal.model.types.PremisesName;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;
import org.xmlobjects.xal.model.types.PremisesType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PremiseAdapter extends AddressObjectAdapter<Premises> {

    @Override
    public Premises createObject(QName name, Object parent) throws ObjectBuildException {
        Premises object = new Premises();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Premises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("PremiseDependency").ifPresent(v -> object.getOtherAttributes().add("PremiseDependency", v));
        attributes.getValue("PremiseDependencyType").ifPresent(v -> object.getOtherAttributes().add("PremiseDependencyType", v));
        attributes.getValue("PremiseThoroughfareConnector").ifPresent(v -> object.getOtherAttributes().add("PremiseThoroughfareConnector", v));
        attributes.getValue("Type").ifPresent(v -> {
            PremisesType type = PremisesType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("Type", v);
        });
    }

    @Override
    public void buildChildObject(Premises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "PremiseName":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNameAdapter.class)));
                    break;
                case "PremiseLocation":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseLocationAdapter.class)));
                    break;
                case "PremiseNumber":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNumberAdapter.class)));
                    break;
                case "PremiseNumberRange":
                    object.getNameElementOrNumber().addAll(reader.getObjectUsingBuilder(PremiseNumberRangeAdapter.class));
                    break;
                case "PremiseNumberPrefix":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNumberPrefixAdapter.class)));
                    break;
                case "PremiseNumberSuffix":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNumberSuffixAdapter.class)));
                    break;
                case "BuildingName":
                    object.getDeprecatedProperties().getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
                    break;
                case "SubPremise":
                    SubPremises subPremise = reader.getObjectUsingBuilder(SubPremiseAdapter.class);
                    object.getSubPremises().add(subPremise);
                    while ((subPremise = subPremise.getDeprecatedProperties().getSubPremise()) != null)
                        object.getSubPremises().add(subPremise);

                    object.getSubPremises().forEach(v -> v.getDeprecatedProperties().setSubPremise(null));
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
                case "Premise":
                    object.getDeprecatedProperties().setPremise(reader.getObjectUsingBuilder(PremiseAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, Premises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("PremiseDependency", object.getOtherAttributes().getValue("PremiseDependency"));
        element.addAttribute("PremiseDependencyType", object.getOtherAttributes().getValue("PremiseDependencyType"));
        element.addAttribute("PremiseThoroughfareConnector", object.getOtherAttributes().getValue("PremiseThoroughfareConnector"));

        if (object.getType() != null)
            element.addAttribute("Type", object.getType().toValue());
        else
            element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(Premises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Address address = object.getParent(Address.class);

        PremiseNamesAndNumbers namesAndNumbers = PremiseNamesAndNumbers.of(object);

        for (PremisesName name : namesAndNumbers.getNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseName"), name, SubPremiseNameAdapter.class, namespaces);

        if (namesAndNumbers.getPremiseLocation() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseLocation"), namesAndNumbers.getPremiseLocation(), PremiseLocationAdapter.class, namespaces);
        else {
            if (!namesAndNumbers.getNumbers().isEmpty()) {
                for (Identifier number : namesAndNumbers.getNumbers())
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumber"), number, PremiseNumberAdapter.class, namespaces);
            } else if (namesAndNumbers.getNumberRange() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberRange"), namesAndNumbers.getNumberRange(), PremiseNumberRangeAdapter.class, namespaces);
        }

        for (Identifier prefix : namesAndNumbers.getPrefixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberPrefix"), prefix, PremiseNumberPrefixAdapter.class, namespaces);

        for (Identifier suffix : namesAndNumbers.getSuffixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberSuffix"), suffix, PremiseNumberSuffixAdapter.class, namespaces);

        for (Identifier buildingName : object.getDeprecatedProperties().getBuildingNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "BuildingName"), buildingName, BuildingNameAdapter.class, namespaces);

        if (!object.getSubPremises().isEmpty()) {
            ObjectSerializer<SubPremises> serializer = writer.getOrCreateSerializer(SubPremiseAdapter.class);
            for (SubPremises subPremise : object.getSubPremises()) {
                Element element = Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremise");
                serializer.initializeElement(element, subPremise, namespaces, writer);
                writer.writeStartElement(element);
                writer.writeObjectUsingSerializer(subPremise, serializer, namespaces);
            }

            writer.writeEndElements(object.getSubPremises().size());
        } else if (object.getDeprecatedProperties().getFirm() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), object.getDeprecatedProperties().getFirm(), FirmAdapter.class, namespaces);

        PostalDeliveryPoint mailStop = address != null && address.getPostalDeliveryPoint() != null && address.getPostalDeliveryPoint().getType() == PostalDeliveryPointType.MAIL_STOP ?
                address.getPostalDeliveryPoint() :
                object.getDeprecatedProperties().getMailStop();

        if (mailStop != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStop"), mailStop, MailStopAdapter.class, namespaces);

        PostCode postalCode = address != null && address.getLocality() == null && address.getPostCode() != null ?
                address.getPostCode() :
                object.getDeprecatedProperties().getPostalCode();

        if (postalCode != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), postalCode, PostalCodeAdapter.class, namespaces);

        if (object.getDeprecatedProperties().getPremise() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise"), object.getDeprecatedProperties().getPremise(), PremiseAdapter.class, namespaces);
    }
}
