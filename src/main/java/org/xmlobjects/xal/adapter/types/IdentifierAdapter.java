/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

public class IdentifierAdapter implements ObjectBuilder<Identifier>, ObjectSerializer<Identifier> {

    @Override
    public Identifier createObject(QName name, Object parent) throws ObjectBuildException {
        return new Identifier();
    }

    @Override
    public void initializeObject(Identifier object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().collapse().ifPresent(object::setContent);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(IdentifierElementType.fromValue(v)));
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "Abbreviation").ifBoolean(object::setAbbreviation);
        XALBuilderHelper.buildOtherAttributes(object::getOtherAttributes, attributes);
    }

    @Override
    public void initializeElement(Element element, Identifier object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(TextContent.of(object.getContent()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "Abbreviation", TextContent.ofBoolean(object.getAbbreviation()));

        if (object.isSetOtherAttributes()) {
            XALSerializerHelper.addOtherAttributes(element, object.getOtherAttributes(), namespaces);
        }

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());
    }
}
