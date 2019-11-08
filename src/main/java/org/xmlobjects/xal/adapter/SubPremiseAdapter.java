package org.xmlobjects.xal.adapter;

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
import org.xmlobjects.xal.model.SubPremise;
import org.xmlobjects.xal.model.SubPremiseName;
import org.xmlobjects.xal.model.SubPremiseNumber;
import org.xmlobjects.xal.model.SubPremiseNumberPrefix;
import org.xmlobjects.xal.model.SubPremiseNumberSuffix;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseAdapter implements ObjectBuilder<SubPremise>, ObjectSerializer<SubPremise> {

    @Override
    public SubPremise createObject(QName name) throws ObjectBuildException {
        return new SubPremise();
    }

    @Override
    public void initializeObject(SubPremise object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(SubPremise object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "SubPremiseName":
                    object.getSubPremiseNames().add(reader.getObjectUsingBuilder(SubPremiseNameAdapter.class));
                    break;
                case "SubPremiseLocation":
                    object.setSubPremiseLocation(reader.getObjectUsingBuilder(SubPremiseLocationAdapter.class));
                    break;
                case "SubPremiseNumber":
                    object.getSubPremiseNumbers().add(reader.getObjectUsingBuilder(SubPremiseNumberAdapter.class));
                    break;
                case "SubPremiseNumberPrefix":
                    object.getSubPremiseNumberPrefixes().add(reader.getObjectUsingBuilder(SubPremiseNumberPrefixAdapter.class));
                    break;
                case "SubPremiseNumberSuffix":
                    object.getSubPremiseNumberSuffixes().add(reader.getObjectUsingBuilder(SubPremiseNumberSuffixAdapter.class));
                    break;
                case "BuildingName":
                    object.getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
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
                case "SubPremise":
                    object.setSubPremise(reader.getObjectUsingBuilder(SubPremiseAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(SubPremise object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremise");
    }

    @Override
    public void initializeElement(Element element, SubPremise object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(SubPremise object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (SubPremiseName subPremiseName : object.getSubPremiseNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseName"), subPremiseName, SubPremiseNameAdapter.class, namespaces);

        if (object.isSetSubPremiseLocation())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseLocation"), object.getSubPremiseLocation(), SubPremiseLocationAdapter.class, namespaces);
        else if (object.isSetSubPremiseNumbers()) {
            for (SubPremiseNumber subPremiseNumber : object.getSubPremiseNumbers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumber"), subPremiseNumber, SubPremiseNumberAdapter.class, namespaces);
        }

        for (SubPremiseNumberPrefix subPremiseNumberPrefix : object.getSubPremiseNumberPrefixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumberPrefix"), subPremiseNumberPrefix, SubPremiseNumberPrefixAdapter.class, namespaces);

        for (SubPremiseNumberSuffix subPremiseNumberSuffix : object.getSubPremiseNumberSuffixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumberSuffix"), subPremiseNumberSuffix, SubPremiseNumberSuffixAdapter.class, namespaces);

        for (BuildingName buildingName : object.getBuildingNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "BuildingName"), buildingName, BuildingNameAdapter.class, namespaces);

        if (object.getFirm() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), object.getFirm(), FirmAdapter.class, namespaces);

        if (object.getMailStop() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStop"), object.getMailStop(), MailStopAdapter.class, namespaces);

        if (object.getPostalCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        if (object.getSubPremise() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremise"), object.getSubPremise(), SubPremiseAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
