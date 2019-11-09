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
import org.xmlobjects.xal.model.IndicatorOccurrence;
import org.xmlobjects.xal.model.NumberType;
import org.xmlobjects.xal.model.NumberTypeOccurrence;
import org.xmlobjects.xal.model.PremiseNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "PremiseNumber", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class PremiseNumberAdapter implements ObjectBuilder<PremiseNumber>, ObjectSerializer<PremiseNumber> {

    @Override
    public PremiseNumber createObject(QName name) throws ObjectBuildException {
        return new PremiseNumber();
    }

    @Override
    public void initializeObject(PremiseNumber object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NumberType").ifPresent(v -> object.setNumberType(NumberType.fromValue(v)));
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("NumberTypeOccurrence").ifPresent(v -> object.setNumberTypeOccurrence(NumberTypeOccurrence.fromValue(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(PremiseNumber object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumber");
    }

    @Override
    public void initializeElement(Element element, PremiseNumber object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("Code", object.getCode());

        if (object.getNumberType() != null)
            element.addAttribute("NumberType", object.getNumberType().toValue());

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        if (object.getNumberTypeOccurrence() != null)
            element.addAttribute("NumberTypeOccurrence", object.getNumberTypeOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
