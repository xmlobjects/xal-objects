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
import org.xmlobjects.xal.adapter.types.CountryNameAdapter;
import org.xmlobjects.xal.model.Country;
import org.xmlobjects.xal.model.types.CountryName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class CountryAdapter extends AddressObjectAdapter<Country> {

    @Override
    public Country createObject(QName name, Object parent) throws ObjectBuildException {
        return new Country();
    }

    @Override
    public void buildChildObject(Country object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI()) && "NameElement".equals(name.getLocalPart()))
            object.getNameElements().add(reader.getObjectUsingBuilder(CountryNameAdapter.class));
    }

    @Override
    public void writeChildElements(Country object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetNameElements()) {
            for (CountryName name : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "NameElement"), name, CountryNameAdapter.class, namespaces);
        }
    }
}
