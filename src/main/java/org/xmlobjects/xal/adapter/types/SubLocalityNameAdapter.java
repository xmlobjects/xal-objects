/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.types.SubLocalityName;
import org.xmlobjects.xal.model.types.SubLocalityNameType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class SubLocalityNameAdapter extends NameWithCodeAdapter<SubLocalityName> {

    @Override
    public SubLocalityName createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubLocalityName();
    }

    @Override
    public void initializeObject(SubLocalityName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "NameType").ifPresent(v -> object.setNameType(SubLocalityNameType.fromValue(v)));
    }
}
