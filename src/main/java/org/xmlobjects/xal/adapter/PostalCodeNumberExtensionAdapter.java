package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.PostalCodeNumberExtension;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostalCodeNumberExtensionAdapter implements ObjectBuilder<PostalCodeNumberExtension>, ObjectSerializer<PostalCodeNumberExtension> {

    @Override
    public PostalCodeNumberExtension createObject(QName name) throws ObjectBuildException {
        return new PostalCodeNumberExtension();
    }

    @Override
    public void initializeObject(PostalCodeNumberExtension object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("NumberExtensionSeparator").ifPresent(object::setNumberExtensionSeparator);
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(PostalCodeNumberExtension object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCodeNumberExtension");
    }

    @Override
    public void initializeElement(Element element, PostalCodeNumberExtension object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Type", object.getType());
        element.addAttribute("NumberExtensionSeparator", object.getNumberExtensionSeparator());
        element.addAttribute("Code", object.getCode());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
