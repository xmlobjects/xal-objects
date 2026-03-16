/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class PremisesName extends Name<PremisesNameType> {

    public PremisesName() {
        super();
    }

    public PremisesName(String content, PremisesNameType nameType) {
        super(content, nameType);
    }

    public PremisesName(String content) {
        super(content);
    }

    public PremisesName(PremisesNameType nameType) {
        super(nameType);
    }
}
