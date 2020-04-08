/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.GenericElement;
import org.xmlobjects.xml.Attribute;
import org.xmlobjects.xml.Attributes;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

public class XALBuilderHelper {

    public static void buildOtherAttributes(Map<QName, String> otherAttributes, Attributes attributes) {
        for (Attribute attribute : attributes.toList()) {
            if (!XMLConstants.NULL_NS_URI.equals(attribute.getNamespaceURI())
                    && !XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI.equals(attribute.getNamespaceURI())
                    && !XMLConstants.XML_NS_URI.equals(attribute.getNamespaceURI())
                    && !XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attribute.getNamespaceURI())
                    && !XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(attribute.getNamespaceURI()))
                otherAttributes.put(new QName(attribute.getNamespaceURI(), attribute.getLocalName()), attribute.getValue().get());
        }
    }

    public static void buildGenericElements(List<GenericElement> genericElements, XMLReader reader) throws XMLReadException {
        if (reader.isCreateDOMAsFallback())
            genericElements.add(GenericElement.of(reader.getDOMElement()));
    }
}
