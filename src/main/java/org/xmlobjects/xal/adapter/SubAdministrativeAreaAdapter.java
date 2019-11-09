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
import org.xmlobjects.xal.model.SubAdministrativeArea;
import org.xmlobjects.xal.model.SubAdministrativeAreaName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubAdministrativeAreaAdapter implements ObjectBuilder<SubAdministrativeArea>, ObjectSerializer<SubAdministrativeArea> {

    @Override
    public SubAdministrativeArea createObject(QName name) throws ObjectBuildException {
        return new SubAdministrativeArea();
    }

    @Override
    public void initializeObject(SubAdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("UsageType").ifPresent(object::setUsageType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(SubAdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "SubAdministrativeAreaName":
                    object.getSubAdministrativeAreaNames().add(reader.getObjectUsingBuilder(SubAdministrativeAreaNameAdapter.class));
                    break;
                case "Locality":
                    object.setLocality(reader.getObjectUsingBuilder(LocalityAdapter.class));
                    break;
                case "PostOffice":
                    object.setPostOffice(reader.getObjectUsingBuilder(PostOfficeAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(SubAdministrativeArea object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubAdministrativeArea");
    }

    @Override
    public void initializeElement(Element element, SubAdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        element.addAttribute("UsageType", object.getUsageType());
        element.addAttribute("Indicator", object.getIndicator());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(SubAdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (SubAdministrativeAreaName subAdministrativeAreaName : object.getSubAdministrativeAreaNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubAdministrativeAreaName"), subAdministrativeAreaName, SubAdministrativeAreaNameAdapter.class, namespaces);

        if (object.isSetLocality())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), object.getLocality(), LocalityAdapter.class, namespaces);
        else if (object.isSetPostOffice())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOffice"), object.getPostOffice(), PostOfficeAdapter.class, namespaces);
        else if (object.isSetPostalCode())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
