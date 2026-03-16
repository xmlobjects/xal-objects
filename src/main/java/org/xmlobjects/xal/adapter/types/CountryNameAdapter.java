/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.types.CountryName;
import org.xmlobjects.xal.model.types.CountryNameType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class CountryNameAdapter extends NameWithCodeAdapter<CountryName> {

    @Override
    public CountryName createObject(QName name, Object parent) throws ObjectBuildException {
        return new CountryName();
    }

    @Override
    public void initializeObject(CountryName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "NameType").ifPresent(v -> object.setNameType(CountryNameType.fromValue(v)));
    }
}
