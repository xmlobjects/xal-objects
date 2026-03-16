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
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.PostalDeliveryPointType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostalDeliveryPointAdapter extends AddressObjectAdapter<PostalDeliveryPoint> {

    @Override
    public PostalDeliveryPoint createObject(QName name, Object parent) throws ObjectBuildException {
        return new PostalDeliveryPoint();
    }

    @Override
    public void initializeObject(PostalDeliveryPoint object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(PostalDeliveryPointType.fromValue(v)));
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(PostalDeliveryPoint object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI()) && "Identifier".equals(name.getLocalPart()))
            object.getIdentifiers().add(reader.getObjectUsingBuilder(IdentifierAdapter.class));
    }

    @Override
    public void initializeElement(Element element, PostalDeliveryPoint object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        XALSerializerHelper.addDataQualityAttributes(element, object);

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());
    }

    @Override
    public void writeChildElements(PostalDeliveryPoint object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetIdentifiers()) {
            for (Identifier identifier : object.getIdentifiers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Identifier"), identifier, IdentifierAdapter.class, namespaces);
        }
    }
}
