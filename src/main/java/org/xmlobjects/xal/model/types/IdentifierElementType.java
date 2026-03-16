/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum IdentifierElementType {
    NAME("Name"),
    RANGE_FROM("RangeFrom"),
    RANGE("Range"),
    RANGE_TO("RangeTo"),
    PREFIX("Prefix"),
    SUFFIX("Suffix"),
    NUMBER("Number"),
    SEPARATOR("Separator"),
    EXTENSION("Extension");

    private final String value;

    IdentifierElementType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static IdentifierElementType fromValue(String value) {
        for (IdentifierElementType v : IdentifierElementType.values()) {
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
