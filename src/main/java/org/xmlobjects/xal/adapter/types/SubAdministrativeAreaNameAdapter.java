/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaName;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaNameType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class SubAdministrativeAreaNameAdapter extends NameWithCodeAdapter<SubAdministrativeAreaName> {

    @Override
    public SubAdministrativeAreaName createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubAdministrativeAreaName();
    }

    @Override
    public void initializeObject(SubAdministrativeAreaName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "NameType").ifPresent(v -> object.setNameType(SubAdministrativeAreaNameType.fromValue(v)));
    }
}
