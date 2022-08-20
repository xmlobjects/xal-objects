/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2022 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.adapter.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.XALBuilderHelper;
import org.xmlobjects.xal.adapter.XALSerializerHelper;
import org.xmlobjects.xal.model.types.DirectionType;
import org.xmlobjects.xal.model.types.Longitude;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

public class LongitudeAdapter implements ObjectBuilder<Longitude>, ObjectSerializer<Longitude> {

    @Override
    public Longitude createObject(QName name, Object parent) throws ObjectBuildException {
        return new Longitude();
    }

    @Override
    public void initializeObject(Longitude object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "DegreesMeasure").collapse().ifPresent(object::setDegreesMeasure);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "MinutesMeasure").collapse().ifPresent(object::setMinutesMeasure);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "SecondsMeasure").collapse().ifPresent(object::setSecondsMeasure);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Direction").ifPresent(v -> object.setDirection(DirectionType.fromValue(v)));
        XALBuilderHelper.buildOtherAttributes(object::getOtherAttributes, attributes);
    }

    @Override
    public void initializeElement(Element element, Longitude object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "DegreesMeasure", TextContent.of(object.getDegreesMeasure()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "MinutesMeasure", TextContent.of(object.getMinutesMeasure()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "SecondsMeasure", TextContent.of(object.getSecondsMeasure()).collapse());

        if (object.isSetOtherAttributes()) {
            XALSerializerHelper.addOtherAttributes(element, object.getOtherAttributes(), namespaces);
        }

        if (object.getDirection() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Direction", object.getDirection().toValue());
    }
}
