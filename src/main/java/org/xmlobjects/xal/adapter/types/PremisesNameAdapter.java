/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.model.types.PremisesName;
import org.xmlobjects.xal.model.types.PremisesNameType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class PremisesNameAdapter extends NameAdapter<PremisesName> {

    @Override
    public PremisesName createObject(QName name, Object parent) throws ObjectBuildException {
        return new PremisesName();
    }

    @Override
    public void initializeObject(PremisesName object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "NameType").ifPresent(v -> object.setNameType(PremisesNameType.fromValue(v)));
    }
}
