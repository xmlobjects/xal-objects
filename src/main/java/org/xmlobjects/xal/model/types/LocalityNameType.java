/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum LocalityNameType implements NameType {
    NAME("Name"),
    NUMBER("Number"),
    REFERENCE_LOCATION("ReferenceLocation"),
    TYPE("Type");

    private final String value;

    LocalityNameType(String value) {
        this.value = value;
    }

    @Override
    public String toValue() {
        return value;
    }

    public static LocalityNameType fromValue(String value) {
        for (LocalityNameType v : LocalityNameType.values()) {
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
