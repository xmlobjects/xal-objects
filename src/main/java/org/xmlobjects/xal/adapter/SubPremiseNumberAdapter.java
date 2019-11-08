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
import org.xmlobjects.xal.model.NumberTypeOccurrence;
import org.xmlobjects.xal.model.SubPremiseNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseNumberAdapter implements ObjectBuilder<SubPremiseNumber>, ObjectSerializer<SubPremiseNumber> {

    @Override
    public SubPremiseNumber createObject(QName name) throws ObjectBuildException {
        return new SubPremiseNumber();
    }

    @Override
    public void initializeObject(SubPremiseNumber object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("NumberTypeOccurrence").ifPresent(v -> object.setNumberTypeOccurrence(NumberTypeOccurrence.fromValue(v)));
        attributes.getValue("PremiseNumberSeparator").ifPresent(object::setPremiseNumberSeparator);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(SubPremiseNumber object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumber");
    }

    @Override
    public void initializeElement(Element element, SubPremiseNumber object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("PremiseNumberSeparator", object.getPremiseNumberSeparator());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Code", object.getCode());

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        if (object.getNumberTypeOccurrence() != null)
            element.addAttribute("NumberTypeOccurrence", object.getNumberTypeOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
