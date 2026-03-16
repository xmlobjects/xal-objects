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
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostCodeAdapter extends AddressObjectAdapter<PostCode> {

    @Override
    public PostCode createObject(QName name, Object parent) throws ObjectBuildException {
        return new PostCode();
    }

    @Override
    public void initializeObject(PostCode object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(PostCode object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI()) && "Identifier".equals(name.getLocalPart()))
            object.getIdentifiers().add(reader.getObjectUsingBuilder(IdentifierAdapter.class));
    }

    @Override
    public void initializeElement(Element element, PostCode object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        XALSerializerHelper.addDataQualityAttributes(element, object);
    }

    @Override
    public void writeChildElements(PostCode object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetIdentifiers()) {
            for (Identifier identifier : object.getIdentifiers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Identifier"), identifier, IdentifierAdapter.class, namespaces);
        }
    }
}
