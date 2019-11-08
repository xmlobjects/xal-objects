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
import org.xmlobjects.xal.model.Premise;
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

    }

    @Override
    public void buildChildObject(Premise object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {

    }

    @Override
    public Element createElement(Premise object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise");
    }

    @Override
    public void initializeElement(Element element, Premise object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

    }

    @Override
    public void writeChildElements(Premise object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

    }
}
