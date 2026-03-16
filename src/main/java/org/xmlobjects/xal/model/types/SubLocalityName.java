/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class SubLocalityName extends NameWithCode<SubLocalityNameType> {

    public SubLocalityName() {
        super();
    }

    public SubLocalityName(String content, SubLocalityNameType nameType) {
        super(content, nameType);
    }

    public SubLocalityName(String content) {
        super(content);
    }

    public SubLocalityName(SubLocalityNameType nameType) {
        super(nameType);
    }
}
