/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum SubLocalityType {
    MUNICIPALITY("Municipality"),
    VILLAGE("Village");

    private final String value;

    SubLocalityType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static SubLocalityType fromValue(String value) {
        for (SubLocalityType v : SubLocalityType.values()) {
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
