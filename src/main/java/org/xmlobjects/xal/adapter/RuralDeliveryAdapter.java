/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.types.IdentifierAdapter;
import org.xmlobjects.xal.model.RuralDelivery;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

public class RuralDeliveryAdapter extends AddressObjectAdapter<RuralDelivery> {

    @Override
    public RuralDelivery createObject(QName name, Object parent) throws ObjectBuildException {
        return new RuralDelivery();
    }

    @Override
    public void initializeObject(RuralDelivery object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").normalize().ifPresent(object::setType);
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(RuralDelivery object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI()) && "Identifier".equals(name.getLocalPart()))
            object.getIdentifiers().add(reader.getObjectUsingBuilder(IdentifierAdapter.class));
    }

    @Override
    public void initializeElement(Element element, RuralDelivery object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", TextContent.of(object.getType()).normalize());
        XALSerializerHelper.addDataQualityAttributes(element, object);
    }

    @Override
    public void writeChildElements(RuralDelivery object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetIdentifiers()) {
            for (Identifier identifier : object.getIdentifiers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Identifier"), identifier, IdentifierAdapter.class, namespaces);
        }
    }
}
