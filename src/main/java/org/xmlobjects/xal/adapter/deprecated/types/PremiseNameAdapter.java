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
import org.xmlobjects.xal.model.types.PremisesName;
import org.xmlobjects.xal.model.types.PremisesNameType;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PremiseNameAdapter extends NameWithTypeAdapter<PremisesName> {

    @Override
    public PremisesName createObject(QName name, Object parent) throws ObjectBuildException {
        return new PremisesName(PremisesNameType.NAME);
    }

    @Override
    public void initializeObject(PremisesName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("TypeOccurrence").ifPresent(v -> object.getOtherAttributes().add("TypeOccurrence", v));
    }

    @Override
    public void initializeElement(Element element, PremisesName object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("TypeOccurrence", object.getOtherAttributes().getValue("TypeOccurrence"));
    }
}
