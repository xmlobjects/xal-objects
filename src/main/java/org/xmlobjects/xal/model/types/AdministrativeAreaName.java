/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class AdministrativeAreaName extends NameWithCode<AdministrativeAreaNameType> {

    public AdministrativeAreaName() {
        super();
    }

    public AdministrativeAreaName(String content, AdministrativeAreaNameType nameType) {
        super(content, nameType);
    }

    public AdministrativeAreaName(String content) {
        super(content);
    }

    public AdministrativeAreaName(AdministrativeAreaNameType nameType) {
        super(nameType);
    }
}
