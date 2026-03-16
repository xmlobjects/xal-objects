/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.LocalityName;
import org.xmlobjects.xal.model.types.LocalityNameType;

import javax.xml.namespace.QName;

public class LocalityNameAdapter extends NameWithTypeAdapter<LocalityName> {

    @Override
    public LocalityName createObject(QName name, Object parent) throws ObjectBuildException {
        return new LocalityName(LocalityNameType.NAME);
    }
}
