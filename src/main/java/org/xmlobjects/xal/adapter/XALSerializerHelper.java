package org.xmlobjects.xal.adapter;

import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.GenericElement;
import org.xmlobjects.xml.Element;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

public class XALSerializerHelper {

    public static void serializeOtherAttributes(Element element, Map<QName, String> otherAttributes) {
        otherAttributes.forEach(element::addAttribute);
    }

    public static void serializeGenericElements(List<GenericElement> genericElements, XMLWriter writer) throws XMLWriteException {
        for (GenericElement element : genericElements)
            writer.writeDOMElement(element.getContent());
    }
}
