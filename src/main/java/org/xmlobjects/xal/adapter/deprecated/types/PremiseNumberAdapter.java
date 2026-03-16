/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
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

public class PremiseNumberAdapter extends IdentifierWithTypeAdapter<Identifier> {

    @Override
    public Identifier createObject(QName name, Object parent) throws ObjectBuildException {
        return new Identifier();
    }

    @Override
    public void initializeObject(Identifier object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Indicator").ifPresent(v -> object.getOtherAttributes().add("Indicator", v));
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.getOtherAttributes().add("IndicatorOccurrence", v));
        attributes.getValue("NumberTypeOccurrence").ifPresent(v -> object.getOtherAttributes().add("NumberTypeOccurrence", v));
        object.setType("Range".equals(attributes.getValue("NumberType").get()) ?
                IdentifierElementType.RANGE :
                IdentifierElementType.NUMBER);
    }

    @Override
    public void initializeElement(Element element, Identifier object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Indicator", object.getOtherAttributes().getValue("Indicator"));
        element.addAttribute("IndicatorOccurrence", object.getOtherAttributes().getValue("IndicatorOccurrence"));
        element.addAttribute("NumberTypeOccurrence", object.getOtherAttributes().getValue("NumberTypeOccurrence"));

        if (object.getType() == IdentifierElementType.RANGE)
            element.addAttribute("NumberType", "Range");
    }
}
