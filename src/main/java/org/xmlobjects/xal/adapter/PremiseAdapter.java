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

package org.xmlobjects.xal.adapter;

import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.BuildingName;
import org.xmlobjects.xal.model.Premise;
import org.xmlobjects.xal.model.PremiseName;
import org.xmlobjects.xal.model.PremiseNumber;
import org.xmlobjects.xal.model.PremiseNumberPrefix;
import org.xmlobjects.xal.model.PremiseNumberSuffix;
import org.xmlobjects.xal.model.SubPremise;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "Premise", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class PremiseAdapter implements ObjectBuilder<Premise>, ObjectSerializer<Premise> {

    @Override
    public Premise createObject(QName name) throws ObjectBuildException {
        return new Premise();
    }

    @Override
    public void initializeObject(Premise object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("PremiseDependency").ifPresent(object::setPremiseDependency);
        attributes.getValue("PremiseDependencyType").ifPresent(object::setPremiseDependencyType);
        attributes.getValue("PremiseThoroughfareConnector").ifPresent(object::setPremiseThoroughfareConnector);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(Premise object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "PremiseName":
                    object.getPremiseNames().add(reader.getObjectUsingBuilder(PremiseNameAdapter.class));
                    break;
                case "PremiseLocation":
                    object.setPremiseLocation(reader.getObjectUsingBuilder(PremiseLocationAdapter.class));
                    break;
                case "PremiseNumber":
                    object.getPremiseNumbers().add(reader.getObjectUsingBuilder(PremiseNumberAdapter.class));
                    break;
                case "PremiseNumberRange":
                    object.setPremiseNumberRange(reader.getObjectUsingBuilder(PremiseNumberRangeAdapter.class));
                    break;
                case "PremiseNumberPrefix":
                    object.getPremiseNumberPrefixes().add(reader.getObjectUsingBuilder(PremiseNumberPrefixAdapter.class));
                    break;
                case "PremiseNumberSuffix":
                    object.getPremiseNumberSuffixes().add(reader.getObjectUsingBuilder(PremiseNumberSuffixAdapter.class));
                    break;
                case "BuildingName":
                    object.getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
                    break;
                case "SubPremise":
                    object.getSubPremises().add(reader.getObjectUsingBuilder(SubPremiseAdapter.class));
                    break;
                case "Firm":
                    object.setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
                    break;
                case "MailStop":
                    object.setMailStop(reader.getObjectUsingBuilder(MailStopAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
                case "Premise":
                    object.setPremise(reader.getObjectUsingBuilder(PremiseAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(Premise object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise");
    }

    @Override
    public void initializeElement(Element element, Premise object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        element.addAttribute("PremiseDependency", object.getPremiseDependency());
        element.addAttribute("PremiseDependencyType", object.getPremiseDependencyType());
        element.addAttribute("PremiseThoroughfareConnector", object.getPremiseThoroughfareConnector());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(Premise object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (PremiseName premiseName : object.getPremiseNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseName"), premiseName, PremiseNameAdapter.class, namespaces);

        if (object.isSetPremiseLocation())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseLocation"), object.getPremiseLocation(), PremiseLocationAdapter.class, namespaces);
        else if (object.isSetPremiseNumbers()) {
            for (PremiseNumber premiseNumber : object.getPremiseNumbers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumber"), premiseNumber, PremiseNumberAdapter.class, namespaces);
        } else if (object.isSetPremiseNumberRange())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberRange"), object.getPremiseNumberRange(), PremiseNumberRangeAdapter.class, namespaces);

        for (PremiseNumberPrefix premiseNumberPrefix : object.getPremiseNumberPrefixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberPrefix"), premiseNumberPrefix, PremiseNumberPrefixAdapter.class, namespaces);

        for (PremiseNumberSuffix premiseNumberSuffix : object.getPremiseNumberSuffixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberSuffix"), premiseNumberSuffix, PremiseNumberSuffixAdapter.class, namespaces);

        for (BuildingName buildingName : object.getBuildingNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "BuildingName"), buildingName, BuildingNameAdapter.class, namespaces);

        if (object.isSetSubPremises()) {
            for (SubPremise subPremise : object.getSubPremises())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremise"), subPremise, SubPremiseAdapter.class, namespaces);
        } if (object.isSetFirm())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), object.getFirm(), FirmAdapter.class, namespaces);

        if (object.getMailStop() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStop"), object.getMailStop(), MailStopAdapter.class, namespaces);

        if (object.getPostalCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        if (object.getPremise() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise"), object.getPremise(), PremiseAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
