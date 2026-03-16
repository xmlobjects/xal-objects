/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum SubPremisesType {
    ROOM("Room"),
    SUITE("Suite"),
    APARTMENT("Apartment"),
    SHOP("Shop"),
    OFFICE("Office"),
    UNIT("Unit");

    private final String value;

    SubPremisesType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static SubPremisesType fromValue(String value) {
        for (SubPremisesType v : SubPremisesType.values()) {
            if (v.value.equalsIgnoreCase(value))
                return v;
        }

        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
