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
import org.xmlobjects.util.composite.CompositeObjectAdapter;
import org.xmlobjects.xal.model.deprecated.types.AddressIdentifier;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class AddressIdentifierAdapter extends CompositeObjectAdapter<AddressIdentifier> {

    public AddressIdentifierAdapter() {
        super(PostalServiceElementAdapter.class);
    }

    @Override
    public AddressIdentifier createObject(QName name, Object parent) throws ObjectBuildException {
        return new AddressIdentifier();
    }

    @Override
    public void initializeObject(AddressIdentifier object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("IdentifierType").ifPresent(object::setIdentifierType);
    }

    @Override
    public void initializeElement(Element element, AddressIdentifier object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("IdentifierType", object.getIdentifierType());
    }
}
