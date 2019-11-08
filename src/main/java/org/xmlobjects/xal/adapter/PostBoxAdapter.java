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
import org.xmlobjects.xal.model.PostBox;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "PostBox", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class PostBoxAdapter implements ObjectBuilder<PostBox>, ObjectSerializer<PostBox> {

    @Override
    public PostBox createObject(QName name) throws ObjectBuildException {
        return new PostBox();
    }

    @Override
    public void initializeObject(PostBox object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {

    }

    @Override
    public void buildChildObject(PostBox object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {

    }

    @Override
    public Element createElement(PostBox object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox");
    }

    @Override
    public void initializeElement(Element element, PostBox object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

    }

    @Override
    public void writeChildElements(PostBox object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

    }
}
