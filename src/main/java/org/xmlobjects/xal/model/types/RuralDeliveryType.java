/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum RuralDeliveryType {
    MUNICIPALITY("Municipality"),
    POST_TOWN("PostTown"),
    PLACE("Place"),
    SUBURB("Suburb"),
    TOWN("Town"),
    VILLAGE("Village"),
    AREA("Area"),
    ZONE("Zone");

    private final String value;

    RuralDeliveryType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static RuralDeliveryType fromValue(String value) {
        for (RuralDeliveryType v : RuralDeliveryType.values()) {
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
