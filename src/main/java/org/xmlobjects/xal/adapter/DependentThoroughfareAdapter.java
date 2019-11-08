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
import org.xmlobjects.xal.model.DependentThoroughfare;
import org.xmlobjects.xal.model.ThoroughfareName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class DependentThoroughfareAdapter implements ObjectBuilder<DependentThoroughfare>, ObjectSerializer<DependentThoroughfare> {

    @Override
    public DependentThoroughfare createObject(QName name) throws ObjectBuildException {
        return new DependentThoroughfare();
    }

    @Override
    public void initializeObject(DependentThoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(DependentThoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
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
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(DependentThoroughfare object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "DependentThoroughfare");
    }

    @Override
    public void initializeElement(Element element, DependentThoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(DependentThoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

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

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
