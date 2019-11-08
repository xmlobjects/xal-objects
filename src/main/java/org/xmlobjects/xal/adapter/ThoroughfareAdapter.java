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
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.DependentThoroughfares;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.model.ThoroughfareName;
import org.xmlobjects.xal.model.ThoroughfareNumberOrRange;
import org.xmlobjects.xal.model.ThoroughfareNumberPrefix;
import org.xmlobjects.xal.model.ThoroughfareNumberSuffix;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "Thoroughfare", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class ThoroughfareAdapter implements ObjectBuilder<Thoroughfare>, ObjectSerializer<Thoroughfare> {

    @Override
    public Thoroughfare createObject(QName name) throws ObjectBuildException {
        return new Thoroughfare();
    }

    @Override
    public void initializeObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("DependentThoroughfares").ifPresent(v -> object.setDependentThoroughfares(DependentThoroughfares.fromValue(v)));
        attributes.getValue("DependentThoroughfaresIndicator").ifPresent(object::setDependentThoroughfaresIndicator);
        attributes.getValue("DependentThoroughfaresConnector").ifPresent(object::setDependentThoroughfaresConnector);
        attributes.getValue("DependentThoroughfaresType").ifPresent(object::setDependentThoroughfaresType);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "ThoroughfareNumber":
                    object.getThoroughfareNumberOrRanges().add(new ThoroughfareNumberOrRange(reader.getObjectUsingBuilder(ThoroughfareNumberAdapter.class)));
                    break;
                case "ThoroughfareNumberRange":
                    object.getThoroughfareNumberOrRanges().add(new ThoroughfareNumberOrRange(reader.getObjectUsingBuilder(ThoroughfareNumberRangeAdapter.class)));
                    break;
                case "ThoroughfareNumberPrefix":
                    object.getThoroughfareNumberPrefixes().add(reader.getObjectUsingBuilder(ThoroughfareNumberPrefixAdapter.class));
                    break;
                case "ThoroughfareNumberSuffix":
                    object.getThoroughfareNumberSuffixes().add(reader.getObjectUsingBuilder(ThoroughfareNumberSuffixAdapter.class));
                    break;
                case "ThoroughfarePreDirection":
                    object.setThoroughfarePreDirection(reader.getObjectUsingBuilder(ThoroughfarePreDirectionAdapter.class));
                    break;
                case "ThoroughfareLeadingType":
                    object.setThoroughfareLeadingType(reader.getObjectUsingBuilder(ThoroughfareLeadingTypeAdapter.class));
                    break;
                case "ThoroughfareName":
                    object.getThoroughfareNames().add(reader.getObjectUsingBuilder(ThoroughfareNameAdapter.class));
                    break;
                case "ThoroughfareTrailingType":
                    object.setThoroughfareTrailingType(reader.getObjectUsingBuilder(ThoroughfareTrailingTypeAdapter.class));
                    break;
                case "ThoroughfarePostDirection":
                    object.setThoroughfarePostDirection(reader.getObjectUsingBuilder(ThoroughfarePostDirectionAdapter.class));
                    break;
                case "DependentThoroughfare":
                    object.setDependentThoroughfare(reader.getObjectUsingBuilder(DependentThoroughfareAdapter.class));
                    break;
                case "DependentLocality":
                    object.setDependentLocality(reader.getObjectUsingBuilder(DependentLocalityAdapter.class));
                    break;
                case "Premise":
                    object.setPremise(reader.getObjectUsingBuilder(PremiseAdapter.class));
                    break;
                case "Firm":
                    object.setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(Thoroughfare object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare");
    }

    @Override
    public void initializeElement(Element element, Thoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        element.addAttribute("DependentThoroughfaresIndicator", object.getDependentThoroughfaresIndicator());
        element.addAttribute("DependentThoroughfaresConnector", object.getDependentThoroughfaresConnector());
        element.addAttribute("DependentThoroughfaresType", object.getDependentThoroughfaresType());

        if (object.getDependentThoroughfares() != null)
            element.addAttribute("DependentThoroughfares", object.getDependentThoroughfares().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(Thoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (ThoroughfareNumberOrRange content : object.getThoroughfareNumberOrRanges()) {
            if (content.isSetThoroughfareNumber())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumber"), content.getThoroughfareNumber(), ThoroughfareNumberAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumberRange())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberRange"), content.getThoroughfareNumberRange(), ThoroughfareNumberRangeAdapter.class, namespaces);
        }

        for (ThoroughfareNumberPrefix thoroughfareNumberPrefix : object.getThoroughfareNumberPrefixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberPrefix"), thoroughfareNumberPrefix, ThoroughfareNumberPrefixAdapter.class, namespaces);

        for (ThoroughfareNumberSuffix thoroughfareNumberSuffix : object.getThoroughfareNumberSuffixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberSuffix"), thoroughfareNumberSuffix, ThoroughfareNumberSuffixAdapter.class, namespaces);

        if (object.getThoroughfarePreDirection() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfarePreDirection"), object.getThoroughfarePreDirection(), ThoroughfarePreDirectionAdapter.class, namespaces);

        if (object.getThoroughfareLeadingType() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareLeadingType"), object.getThoroughfareLeadingType(), ThoroughfareLeadingTypeAdapter.class, namespaces);

        for (ThoroughfareName thoroughfareName : object.getThoroughfareNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareName"), thoroughfareName, ThoroughfareNameAdapter.class, namespaces);

        if (object.getThoroughfareTrailingType() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareTrailingType"), object.getThoroughfareTrailingType(), ThoroughfareTrailingTypeAdapter.class, namespaces);

        if (object.getThoroughfarePostDirection() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfarePostDirection"), object.getThoroughfarePostDirection(), ThoroughfarePostDirectionAdapter.class, namespaces);

        if (object.getDependentThoroughfare() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentThoroughfare"), object.getDependentThoroughfare(), DependentThoroughfareAdapter.class, namespaces);

        if (object.isSetDependentLocality())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentLocality"), object.getDependentLocality(), DependentLocalityAdapter.class, namespaces);
        else if (object.isSetPremise())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Premise"), object.getPremise(), PremiseAdapter.class, namespaces);
        else if (object.isSetFirm())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), object.getFirm(), FirmAdapter.class, namespaces);
        else if (object.isSetPostalCode())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
