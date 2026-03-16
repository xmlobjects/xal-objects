/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum SubAdministrativeAreaType {
    COUNTY("County"),
    DISTRICT("District"),
    PROVINCE("Province"),
    REGION("Region");

    private final String value;

    SubAdministrativeAreaType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static SubAdministrativeAreaType fromValue(String value) {
        for (SubAdministrativeAreaType v : SubAdministrativeAreaType.values()) {
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
