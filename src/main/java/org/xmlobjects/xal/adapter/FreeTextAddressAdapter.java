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
import org.xmlobjects.xal.adapter.types.AddressLineAdapter;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.types.AddressLine;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class FreeTextAddressAdapter extends AddressObjectAdapter<FreeTextAddress> {

    @Override
    public FreeTextAddress createObject(QName name, Object parent) throws ObjectBuildException {
        return new FreeTextAddress();
    }

    @Override
    public void initializeObject(FreeTextAddress object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(FreeTextAddress object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI()) && "AddressLine".equals(name.getLocalPart()))
            object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
    }

    @Override
    public void initializeElement(Element element, FreeTextAddress object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        XALSerializerHelper.addDataQualityAttributes(element, object);
    }

    @Override
    public void writeChildElements(FreeTextAddress object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetAddressLines()) {
            for (AddressLine addressLine : object.getAddressLines())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);
        }
    }
}
