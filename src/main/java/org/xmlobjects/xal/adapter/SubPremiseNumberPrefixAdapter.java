package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.SubPremiseNumberPrefix;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseNumberPrefixAdapter implements ObjectBuilder<SubPremiseNumberPrefix>, ObjectSerializer<SubPremiseNumberPrefix> {

    @Override
    public SubPremiseNumberPrefix createObject(QName name) throws ObjectBuildException {
        return new SubPremiseNumberPrefix();
    }

    @Override
    public void initializeObject(SubPremiseNumberPrefix object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NumberPrefixSeparator").ifPresent(object::setNumberPrefixSeparator);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(SubPremiseNumberPrefix object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumberPrefix");
    }

    @Override
    public void initializeElement(Element element, SubPremiseNumberPrefix object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("NumberPrefixSeparator", object.getNumberPrefixSeparator());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Code", object.getCode());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
