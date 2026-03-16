/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class ThoroughfareName extends Name<ThoroughfareNameType> {

    public ThoroughfareName() {
        super();
    }

    public ThoroughfareName(String content, ThoroughfareNameType nameType) {
        super(content, nameType);
    }

    public ThoroughfareName(String content) {
        super(content);
    }

    public ThoroughfareName(ThoroughfareNameType nameType) {
        super(nameType);
    }
}
