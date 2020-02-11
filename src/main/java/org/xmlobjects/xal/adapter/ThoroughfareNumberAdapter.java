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

import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.IndicatorOccurrence;
import org.xmlobjects.xal.model.NumberOccurrence;
import org.xmlobjects.xal.model.NumberType;
import org.xmlobjects.xal.model.ThoroughfareNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "ThoroughfareNumber", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class ThoroughfareNumberAdapter implements ObjectBuilder<ThoroughfareNumber>, ObjectSerializer<ThoroughfareNumber> {

    @Override
    public ThoroughfareNumber createObject(QName name) throws ObjectBuildException {
        return new ThoroughfareNumber();
    }

    @Override
    public void initializeObject(ThoroughfareNumber object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NumberType").ifPresent(v -> object.setNumberType(NumberType.fromValue(v)));
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("NumberOccurrence").ifPresent(v -> object.setNumberOccurrence(NumberOccurrence.fromValue(v)));
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(ThoroughfareNumber object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumber");
    }

    @Override
    public void initializeElement(Element element, ThoroughfareNumber object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("Code", object.getCode());

        if (object.getNumberType() != null)
            element.addAttribute("NumberType", object.getNumberType().toValue());

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        if (object.getNumberOccurrence() != null)
            element.addAttribute("NumberOccurrence", object.getNumberOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
