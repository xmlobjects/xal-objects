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
import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "AdministrativeArea", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class AdministrativeAreaAdapter implements ObjectBuilder<AdministrativeArea>, ObjectSerializer<AdministrativeArea> {

    @Override
    public AdministrativeArea createObject(QName name) throws ObjectBuildException {
        return new AdministrativeArea();
    }

    @Override
    public void initializeObject(AdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {

    }

    @Override
    public void buildChildObject(AdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {

    }

    @Override
    public Element createElement(AdministrativeArea object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeArea");
    }

    @Override
    public void initializeElement(Element element, AdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

    }

    @Override
    public void writeChildElements(AdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

    }
}
