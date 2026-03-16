/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class SubAdministrativeAreaName extends NameWithCode<SubAdministrativeAreaNameType> {

    public SubAdministrativeAreaName() {
        super();
    }

    public SubAdministrativeAreaName(String content, SubAdministrativeAreaNameType nameType) {
        super(content, nameType);
    }

    public SubAdministrativeAreaName(String content) {
        super(content);
    }

    public SubAdministrativeAreaName(SubAdministrativeAreaNameType nameType) {
        super(nameType);
    }
}
