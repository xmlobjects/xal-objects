/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaName;

import javax.xml.namespace.QName;

public class SubAdministrativeAreaNameAdapter extends NameWithTypeAdapter<SubAdministrativeAreaName> {

    @Override
    public SubAdministrativeAreaName createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubAdministrativeAreaName();
    }
}
