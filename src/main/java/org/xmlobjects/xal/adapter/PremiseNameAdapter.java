package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.PremiseName;
import org.xmlobjects.xal.model.TypeOccurrence;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PremiseNameAdapter implements ObjectBuilder<PremiseName>, ObjectSerializer<PremiseName> {

    @Override
    public PremiseName createObject(QName name) throws ObjectBuildException {
        return new PremiseName();
    }

    @Override
    public void initializeObject(PremiseName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("TypeOccurrence").ifPresent(v -> object.setTypeOccurrence(TypeOccurrence.fromValue(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(PremiseName object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseName");
    }

    @Override
    public void initializeElement(Element element, PremiseName object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Code", object.getCode());

        if (object.getTypeOccurrence() != null)
            element.addAttribute("TypeOccurrence", object.getTypeOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
