package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.PostBoxNumberExtension;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostBoxNumberExtensionAdapter implements ObjectBuilder<PostBoxNumberExtension>, ObjectSerializer<PostBoxNumberExtension> {

    @Override
    public PostBoxNumberExtension createObject(QName name) throws ObjectBuildException {
        return new PostBoxNumberExtension();
    }

    @Override
    public void initializeObject(PostBoxNumberExtension object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NumberExtensionSeparator").ifPresent(object::setNumberExtensionSeparator);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(PostBoxNumberExtension object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberExtension");
    }

    @Override
    public void initializeElement(Element element, PostBoxNumberExtension object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("NumberExtensionSeparator", object.getNumberExtensionSeparator());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
