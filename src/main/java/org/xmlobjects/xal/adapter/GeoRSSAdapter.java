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
import org.xmlobjects.xal.model.GenericElement;
import org.xmlobjects.xal.model.GeoRSS;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class GeoRSSAdapter extends AddressObjectAdapter<GeoRSS> {

    @Override
    public GeoRSS createObject(QName name, Object parent) throws ObjectBuildException {
        return new GeoRSS();
    }

    @Override
    public void buildChildObject(GeoRSS object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (reader.isCreateDOMAsFallback() && XALConstants.GEORSS_1_1_NAMESPACE.equals(name.getNamespaceURI()))
            object.getGenericElements().add(GenericElement.of(reader.getDOMElement()));
    }

    @Override
    public void writeChildElements(GeoRSS object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetGenericElements()) {
            for (GenericElement element : object.getGenericElements()) {
                if (XALConstants.GEORSS_1_1_NAMESPACE.equals(element.getNamespaceURI()))
                    writer.writeDOMElement(element.getContent());
            }
        }
    }
}
