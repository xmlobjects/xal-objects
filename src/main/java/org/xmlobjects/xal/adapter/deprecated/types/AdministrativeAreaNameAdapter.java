/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.AdministrativeAreaName;

import javax.xml.namespace.QName;

public class AdministrativeAreaNameAdapter extends NameWithTypeAdapter<AdministrativeAreaName> {

    @Override
    public AdministrativeAreaName createObject(QName name, Object parent) throws ObjectBuildException {
        return new AdministrativeAreaName();
    }
}
