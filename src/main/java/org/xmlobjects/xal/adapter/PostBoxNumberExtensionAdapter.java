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

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.PostBoxNumberExtension;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostBoxNumberExtensionAdapter implements ObjectBuilder<PostBoxNumberExtension>, ObjectSerializer<PostBoxNumberExtension> {

    @Override
    public PostBoxNumberExtension createObject(QName name) throws ObjectBuildException {
        return new PostBoxNumberExtension();
    }

    @Override
    public void initializeObject(PostBoxNumberExtension object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NumberExtensionSeparator").ifPresent(object::setNumberExtensionSeparator);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(PostBoxNumberExtension object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberExtension");
    }

    @Override
    public void initializeElement(Element element, PostBoxNumberExtension object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("NumberExtensionSeparator", object.getNumberExtensionSeparator());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
