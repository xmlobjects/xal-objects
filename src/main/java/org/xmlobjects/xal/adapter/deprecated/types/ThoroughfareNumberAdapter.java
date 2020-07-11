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

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareNumberAdapter extends IdentifierWithTypeAdapter<Identifier> {

    @Override
    public Identifier createObject(QName name, Object parent) throws ObjectBuildException {
        return new Identifier();
    }

    @Override
    public void initializeObject(Identifier object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.getOtherAttributes().add("IndicatorOccurrence", v));
        attributes.getValue("NumberOccurrence").ifPresent(v -> object.getOtherAttributes().add("NumberOccurrence", v));
        object.setType("Range".equals(attributes.getValue("NumberType").get()) ?
                IdentifierElementType.RANGE :
                IdentifierElementType.NUMBER);
    }

    @Override
    public void initializeElement(Element element, Identifier object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));
        element.addAttribute("IndicatorOccurrence", object.getOtherAttributes().getValue("IndicatorOccurrence"));
        element.addAttribute("NumberOccurrence", object.getOtherAttributes().getValue("NumberOccurrence"));

        if (object.getType() == IdentifierElementType.RANGE)
            element.addAttribute("NumberType", "Range");
    }
}
