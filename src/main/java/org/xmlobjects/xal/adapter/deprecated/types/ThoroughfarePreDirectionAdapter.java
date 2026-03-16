/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.ThoroughfareName;
import org.xmlobjects.xal.model.types.ThoroughfareNameType;

import javax.xml.namespace.QName;

public class ThoroughfarePreDirectionAdapter extends NameWithTypeAdapter<ThoroughfareName> {

    @Override
    public ThoroughfareName createObject(QName name, Object parent) throws ObjectBuildException {
        return new ThoroughfareName(ThoroughfareNameType.PRE_DIRECTION);
    }
}
