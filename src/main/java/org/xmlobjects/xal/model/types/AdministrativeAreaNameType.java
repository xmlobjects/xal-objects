/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum AdministrativeAreaNameType implements NameType {
    NAME("Name"),
    NUMBER("Number"),
    REFERENCE_LOCATION("ReferenceLocation"),
    TYPE("Type");

    private final String value;

    AdministrativeAreaNameType(String value) {
        this.value = value;
    }

    @Override
    public String toValue() {
        return value;
    }

    public static AdministrativeAreaNameType fromValue(String value) {
        for (AdministrativeAreaNameType v : AdministrativeAreaNameType.values()) {
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
