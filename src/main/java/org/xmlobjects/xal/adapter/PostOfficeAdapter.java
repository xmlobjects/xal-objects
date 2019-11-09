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
import org.xmlobjects.xal.model.PostOffice;
import org.xmlobjects.xal.model.PostOfficeName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "PostOffice", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class PostOfficeAdapter implements ObjectBuilder<PostOffice>, ObjectSerializer<PostOffice> {

    @Override
    public PostOffice createObject(QName name) throws ObjectBuildException {
        return new PostOffice();
    }

    @Override
    public void initializeObject(PostOffice object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(PostOffice object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "PostOfficeName":
                    object.getPostOfficeNames().add(reader.getObjectUsingBuilder(PostOfficeNameAdapter.class));
                    break;
                case "PostOfficeNumber":
                    object.setPostOfficeNumber(reader.getObjectUsingBuilder(PostOfficeNumberAdapter.class));
                    break;
                case "PostalRoute":
                    object.setPostalRoute(reader.getObjectUsingBuilder(PostalRouteAdapter.class));
                    break;
                case "PostBox":
                    object.setPostBox(reader.getObjectUsingBuilder(PostBoxAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(PostOffice object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOffice");
    }

    @Override
    public void initializeElement(Element element, PostOffice object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        element.addAttribute("Indicator", object.getIndicator());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(PostOffice object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        if (object.isSetPostOfficeNames()) {
            for (PostOfficeName postOfficeName : object.getPostOfficeNames())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOfficeName"), postOfficeName, PostOfficeNameAdapter.class, namespaces);
        } else if (object.isSetPostOfficeNumber())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOfficeNumber"), object.getPostOfficeNumber(), PostOfficeNumberAdapter.class, namespaces);

        if (object.getPostalRoute() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalRoute"), object.getPostalRoute(), PostalRouteAdapter.class, namespaces);

        if (object.getPostBox() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), object.getPostBox(), PostBoxAdapter.class, namespaces);

        if (object.getPostalCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
