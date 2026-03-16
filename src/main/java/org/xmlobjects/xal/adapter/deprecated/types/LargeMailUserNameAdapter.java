/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.PremisesName;
import org.xmlobjects.xal.model.types.PremisesNameType;

import javax.xml.namespace.QName;

public class LargeMailUserNameAdapter extends NameWithTypeAdapter<PremisesName> {

    @Override
    public PremisesName createObject(QName name, Object parent) throws ObjectBuildException {
        return new PremisesName(PremisesNameType.NAME);
    }
}
