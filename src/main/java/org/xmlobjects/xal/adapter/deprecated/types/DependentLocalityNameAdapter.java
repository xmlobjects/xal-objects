/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.SubLocalityName;
import org.xmlobjects.xal.model.types.SubLocalityNameType;

import javax.xml.namespace.QName;

public class DependentLocalityNameAdapter extends NameWithTypeAdapter<SubLocalityName> {

    @Override
    public SubLocalityName createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubLocalityName(SubLocalityNameType.NAME);
    }
}
