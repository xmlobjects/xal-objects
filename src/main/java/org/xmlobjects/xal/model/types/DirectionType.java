/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum DirectionType {
    EAST("East"),
    WEST("West"),
    NORTH("North"),
    SOUTH("South");

    private final String value;

    DirectionType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static DirectionType fromValue(String value) {
        for (DirectionType v : DirectionType.values()) {
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
