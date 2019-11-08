package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.SubPremiseLocation;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseLocationAdapter implements ObjectBuilder<SubPremiseLocation>, ObjectSerializer<SubPremiseLocation> {

    @Override
    public SubPremiseLocation createObject(QName name) throws ObjectBuildException {
        return new SubPremiseLocation();
    }

    @Override
    public void initializeObject(SubPremiseLocation object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("Code").ifPresent(object::setCode);
    }

    @Override
    public Element createElement(SubPremiseLocation object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseLocation");
    }

    @Override
    public void initializeElement(Element element, SubPremiseLocation object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Code", object.getCode());
    }
}
