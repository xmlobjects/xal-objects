package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.DependentLocalityNumber;
import org.xmlobjects.xal.model.NameNumberOccurrence;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class DependentLocalityNumberAdapter implements ObjectBuilder<DependentLocalityNumber>, ObjectSerializer<DependentLocalityNumber> {

    @Override
    public DependentLocalityNumber createObject(QName name) throws ObjectBuildException {
        return new DependentLocalityNumber();
    }

    @Override
    public void initializeObject(DependentLocalityNumber object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NameNumberOccurrence").ifPresent(v -> object.setNameNumberOccurrence(NameNumberOccurrence.fromValue(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(DependentLocalityNumber object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentLocalityNumber");
    }

    @Override
    public void initializeElement(Element element, DependentLocalityNumber object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Code", object.getCode());

        if (object.getNameNumberOccurrence() != null)
            element.addAttribute("NameNumberOccurrence", object.getNameNumberOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
