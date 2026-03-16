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
import org.xmlobjects.xal.adapter.types.SubAdministrativeAreaNameAdapter;
import org.xmlobjects.xal.model.SubAdministrativeArea;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaName;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubAdministrativeAreaAdapter extends AddressObjectAdapter<SubAdministrativeArea> {

    @Override
    public SubAdministrativeArea createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubAdministrativeArea();
    }

    @Override
    public void initializeObject(SubAdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(SubAdministrativeAreaType.fromValue(v)));
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(SubAdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI()) && "NameElement".equals(name.getLocalPart()))
            object.getNameElements().add(reader.getObjectUsingBuilder(SubAdministrativeAreaNameAdapter.class));
    }

    @Override
    public void initializeElement(Element element, SubAdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        XALSerializerHelper.addDataQualityAttributes(element, object);

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());
    }

    @Override
    public void writeChildElements(SubAdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetNameElements()) {
            for (SubAdministrativeAreaName name : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "NameElement"), name, SubAdministrativeAreaNameAdapter.class, namespaces);
        }
    }
}
