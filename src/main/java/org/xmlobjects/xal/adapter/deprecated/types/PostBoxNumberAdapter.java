/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.types;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;

import javax.xml.namespace.QName;

public class PostBoxNumberAdapter extends IdentifierAdapter<Identifier> {

    @Override
    public Identifier createObject(QName name, Object parent) throws ObjectBuildException {
        return new Identifier(IdentifierElementType.NUMBER);
    }
}
