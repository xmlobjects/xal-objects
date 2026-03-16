/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.types.AdministrativeAreaName;
import org.xmlobjects.xal.model.types.AdministrativeAreaNameType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class AdministrativeAreaNameAdapter extends NameWithCodeAdapter<AdministrativeAreaName> {

    @Override
    public AdministrativeAreaName createObject(QName name, Object parent) throws ObjectBuildException {
        return new AdministrativeAreaName();
    }

    @Override
    public void initializeObject(AdministrativeAreaName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "NameType").ifPresent(v -> object.setNameType(AdministrativeAreaNameType.fromValue(v)));
    }
}
