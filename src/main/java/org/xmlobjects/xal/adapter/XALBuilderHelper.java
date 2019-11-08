package org.xmlobjects.xal.adapter;

import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.GenericElement;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.TextContent;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

public class XALBuilderHelper {

    public static void buildOtherAttributes(Map<QName, String> otherAttributes, Attributes attributes) {
        for (Map.Entry<QName, TextContent> entry : attributes.toMap().entrySet()) {
            if (!XMLConstants.NULL_NS_URI.equals(entry.getKey().getNamespaceURI())
                    && !XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI.equals(entry.getKey().getNamespaceURI())
                    && !XMLConstants.XML_NS_URI.equals(entry.getKey().getNamespaceURI())
                    && !XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(entry.getKey().getNamespaceURI())
                    && !XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(entry.getKey().getNamespaceURI()))
                otherAttributes.put(entry.getKey(), entry.getValue().get());
        }
    }

    public static void buildGenericElements(List<GenericElement> genericElements, XMLReader reader) throws XMLReadException {
        if (reader.isCreateDOMAsFallback())
            genericElements.add(GenericElement.of(reader.getDOMElement()));
    }
}
