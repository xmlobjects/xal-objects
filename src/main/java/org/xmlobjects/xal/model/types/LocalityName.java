/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class LocalityName extends NameWithCode<LocalityNameType> {

    public LocalityName() {
        super();
    }

    public LocalityName(String content, LocalityNameType nameType) {
        super(content, nameType);
    }

    public LocalityName(String content) {
        super(content);
    }

    public LocalityName(LocalityNameType nameType) {
        super(nameType);
    }
}
