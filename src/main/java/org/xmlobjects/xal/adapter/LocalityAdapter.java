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
import org.xmlobjects.xal.adapter.types.LocalityNameAdapter;
import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.types.LocalityName;
import org.xmlobjects.xal.model.types.LocalityType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class LocalityAdapter extends AddressObjectAdapter<Locality> {

    @Override
    public Locality createObject(QName name, Object parent) throws ObjectBuildException {
        return new Locality();
    }

    @Override
    public void initializeObject(Locality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(LocalityType.fromValue(v)));
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(Locality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "NameElement":
                    object.getNameElements().add(reader.getObjectUsingBuilder(LocalityNameAdapter.class));
                    break;
                case "SubLocality":
                    object.setSubLocality(reader.getObjectUsingBuilder(SubLocalityAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, Locality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        XALSerializerHelper.addDataQualityAttributes(element, object);

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());
    }

    @Override
    public void writeChildElements(Locality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetNameElements()) {
            for (LocalityName name : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "NameElement"), name, LocalityNameAdapter.class, namespaces);
        }

        if (object.getSubLocality() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "SubLocality"), object.getSubLocality(), SubLocalityAdapter.class, namespaces);
    }
}
