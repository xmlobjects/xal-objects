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
import org.xmlobjects.xal.adapter.types.AdministrativeAreaNameAdapter;
import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.model.types.AdministrativeAreaName;
import org.xmlobjects.xal.model.types.AdministrativeAreaType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class AdministrativeAreaAdapter extends AddressObjectAdapter<AdministrativeArea> {

    @Override
    public AdministrativeArea createObject(QName name, Object parent) throws ObjectBuildException {
        return new AdministrativeArea();
    }

    @Override
    public void initializeObject(AdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(AdministrativeAreaType.fromValue(v)));
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(AdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "NameElement":
                    object.getNameElements().add(reader.getObjectUsingBuilder(AdministrativeAreaNameAdapter.class));
                    break;
                case "SubAdministrativeArea":
                    object.setSubAdministrativeArea(reader.getObjectUsingBuilder(SubAdministrativeAreaAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, AdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        XALSerializerHelper.addDataQualityAttributes(element, object);

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());
    }

    @Override
    public void writeChildElements(AdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetNameElements()) {
            for (AdministrativeAreaName name : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "NameElement"), name, AdministrativeAreaNameAdapter.class, namespaces);
        }

        if (object.getSubAdministrativeArea() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "SubAdministrativeArea"), object.getSubAdministrativeArea(), SubAdministrativeAreaAdapter.class, namespaces);
    }
}
