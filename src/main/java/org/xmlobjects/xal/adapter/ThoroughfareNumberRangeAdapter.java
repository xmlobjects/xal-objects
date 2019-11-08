package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.IndicatorOccurrence;
import org.xmlobjects.xal.model.NumberRangeOccurrence;
import org.xmlobjects.xal.model.RangeType;
import org.xmlobjects.xal.model.ThoroughfareNumberRange;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareNumberRangeAdapter implements ObjectBuilder<ThoroughfareNumberRange>, ObjectSerializer<ThoroughfareNumberRange> {

    @Override
    public ThoroughfareNumberRange createObject(QName name) throws ObjectBuildException {
        return new ThoroughfareNumberRange();
    }

    @Override
    public void initializeObject(ThoroughfareNumberRange object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("RangeType").ifPresent(v -> object.setRangeType(RangeType.fromValue(v)));
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("Separator").ifPresent(object::setSeparator);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("NumberRangeOccurrence").ifPresent(v -> object.setNumberRangeOccurrence(NumberRangeOccurrence.fromValue(v)));
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(ThoroughfareNumberRange object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "ThoroughfareNumberFrom":
                    object.setThoroughfareNumberFrom(reader.getObjectUsingBuilder(ThoroughfareNumberFromAdapter.class));
                    break;
                case "ThoroughfareNumberTo":
                    object.setThoroughfareNumberTo(reader.getObjectUsingBuilder(ThoroughfareNumberToAdapter.class));
                    break;
            }
        }
    }

    @Override
    public Element createElement(ThoroughfareNumberRange object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberRange");
    }

    @Override
    public void initializeElement(Element element, ThoroughfareNumberRange object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("Separator", object.getSeparator());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Code", object.getCode());

        if (object.getRangeType() != null)
            element.addAttribute("RangeType", object.getRangeType().toValue());

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        if (object.getNumberRangeOccurrence() != null)
            element.addAttribute("NumberRangeOccurrence", object.getNumberRangeOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(ThoroughfareNumberRange object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        if (object.getThoroughfareNumberFrom() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberFrom"), object.getThoroughfareNumberFrom(), ThoroughfareNumberFromAdapter.class, namespaces);

        if (object.getThoroughfareNumberTo() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberTo"), object.getThoroughfareNumberTo(), ThoroughfareNumberToAdapter.class, namespaces);
    }
}
