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
import org.xmlobjects.xal.model.NumberRangeOccurrence;
import org.xmlobjects.xal.model.PremiseNumberRange;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PremiseNumberRangeAdapter implements ObjectBuilder<PremiseNumberRange>, ObjectSerializer<PremiseNumberRange> {

    @Override
    public PremiseNumberRange createObject(QName name) throws ObjectBuildException {
        return new PremiseNumberRange();
    }

    @Override
    public void initializeObject(PremiseNumberRange object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("RangeType").ifPresent(object::setRangeType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("Separator").ifPresent(object::setSeparator);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("NumberRangeOccurence").ifPresent(v -> object.setNumberRangeOccurrence(NumberRangeOccurrence.fromValue(v)));
    }

    @Override
    public void buildChildObject(PremiseNumberRange object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "PremiseNumberRangeFrom":
                    object.setPremiseNumberRangeFrom(reader.getObjectUsingBuilder(PremiseNumberRangeFromAdapter.class));
                    break;
                case "PremiseNumberRangeTo":
                    object.setPremiseNumberRangeTo(reader.getObjectUsingBuilder(PremiseNumberRangeToAdapter.class));
                    break;
            }
        }
    }

    @Override
    public Element createElement(PremiseNumberRange object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberRange");
    }

    @Override
    public void initializeElement(Element element, PremiseNumberRange object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("RangeType", object.getRangeType());
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("Separator", object.getSeparator());
        element.addAttribute("Type", object.getType());

        if (object.getRangeType() != null)

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        if (object.getNumberRangeOccurrence() != null)
            element.addAttribute("NumberRangeOccurence", object.getNumberRangeOccurrence().toValue());
    }

    @Override
    public void writeChildElements(PremiseNumberRange object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.getPremiseNumberRangeFrom() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberRangeFrom"), object.getPremiseNumberRangeFrom(), PremiseNumberRangeFromAdapter.class, namespaces);

        if (object.getPremiseNumberRangeTo() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberRangeTo"), object.getPremiseNumberRangeTo(), PremiseNumberRangeToAdapter.class, namespaces);
    }
}
