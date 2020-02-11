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
