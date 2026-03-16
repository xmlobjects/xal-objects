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
import org.xmlobjects.xal.model.SubThoroughfare;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareAdapter extends AbstractThoroughfareAdapter<Thoroughfare> {

    @Override
    public Thoroughfare createObject(QName name, Object parent) throws ObjectBuildException {
        return new Thoroughfare();
    }

    @Override
    public void buildChildObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI())) {
            if ("SubThoroughfare".equals(name.getLocalPart()))
                object.getSubThoroughfares().add(reader.getObjectUsingBuilder(SubThoroughfareAdapter.class));
            else
                super.buildChildObject(object, name, attributes, reader);
        }
    }

    @Override
    public void writeChildElements(Thoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        if (object.isSetSubThoroughfares()) {
            for (SubThoroughfare subThoroughfare : object.getSubThoroughfares())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "SubThoroughfare"), subThoroughfare, SubThoroughfareAdapter.class, namespaces);
        }
    }
}
