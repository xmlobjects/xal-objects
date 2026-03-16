/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum AdministrativeAreaType {
    CITY("City"),
    STATE("State"),
    TERRITORY("Territory"),
    PROVINCE("Province");

    private final String value;

    AdministrativeAreaType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static AdministrativeAreaType fromValue(String value) {
        for (AdministrativeAreaType v : AdministrativeAreaType.values()) {
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
