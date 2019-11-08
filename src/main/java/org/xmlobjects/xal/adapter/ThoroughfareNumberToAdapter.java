package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.ThoroughfareNumberContent;
import org.xmlobjects.xal.model.ThoroughfareNumberTo;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareNumberToAdapter implements ObjectBuilder<ThoroughfareNumberTo>, ObjectSerializer<ThoroughfareNumberTo> {

    @Override
    public ThoroughfareNumberTo createObject(QName name) throws ObjectBuildException {
        return new ThoroughfareNumberTo();
    }

    @Override
    public void initializeObject(ThoroughfareNumberTo object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(v -> object.getContents().add(new ThoroughfareNumberContent(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(ThoroughfareNumberTo object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(AddressLineAdapter.class)));
                    break;
                case "ThoroughfareNumberPrefix":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(ThoroughfareNumberPrefixAdapter.class)));
                    break;
                case "ThoroughfareNumber":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(ThoroughfareNumberAdapter.class)));
                    break;
                case "ThoroughfareNumberSuffix":
                    object.getContents().add(new ThoroughfareNumberContent(reader.getObjectUsingBuilder(ThoroughfareNumberSuffixAdapter.class)));
                    break;
            }
        }

        reader.getTextContent().ifPresent(v -> object.getContents().add(new ThoroughfareNumberContent(v)));
    }

    @Override
    public Element createElement(ThoroughfareNumberTo object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberTo");
    }

    @Override
    public void initializeElement(Element element, ThoroughfareNumberTo object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Code", object.getCode());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(ThoroughfareNumberTo object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (ThoroughfareNumberContent content : object.getContents()) {
            if (content.isSetString())
                writer.writeCharacters(content.getString());
            else if (content.isSetAddressLine())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), content.getAddressLine(), AddressLineAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumberPrefix())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberPrefix"), content.getThoroughfareNumberPrefix(), ThoroughfareNumberPrefixAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumber())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumber"), content.getThoroughfareNumber(), ThoroughfareNumberAdapter.class, namespaces);
            else if (content.isSetThoroughfareNumberSuffix())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumberSuffix"), content.getThoroughfareNumberSuffix(), ThoroughfareNumberSuffixAdapter.class, namespaces);
        }
    }
}
