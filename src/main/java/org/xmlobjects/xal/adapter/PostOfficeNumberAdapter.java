package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.IndicatorOccurrence;
import org.xmlobjects.xal.model.PostOfficeNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostOfficeNumberAdapter implements ObjectBuilder<PostOfficeNumber>, ObjectSerializer<PostOfficeNumber> {

    @Override
    public PostOfficeNumber createObject(QName name) throws ObjectBuildException {
        return new PostOfficeNumber();
    }

    @Override
    public void initializeObject(PostOfficeNumber object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(PostOfficeNumber object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOfficeNumber");
    }

    @Override
    public void initializeElement(Element element, PostOfficeNumber object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("Code", object.getCode());

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
