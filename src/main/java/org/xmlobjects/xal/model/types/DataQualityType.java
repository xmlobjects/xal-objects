/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum DataQualityType {
    VALID("Valid"),
    INVALID("Invalid");

    private final String value;

    DataQualityType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static DataQualityType fromValue(String value) {
        for (DataQualityType v : DataQualityType.values()) {
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
