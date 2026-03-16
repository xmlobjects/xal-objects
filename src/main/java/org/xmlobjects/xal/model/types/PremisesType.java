/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum PremisesType {
    AIRPORT("Airport"),
    AREA("Area"),
    BUILDING("Building"),
    FARM("Farm"),
    HOSPITAL("Hospital"),
    HOUSE("House"),
    LANDMARK("LandMark"),
    LARGE_MAIL_USER("LargeMailUser"),
    LOT("Lot"),
    RAILWAY_STATION("RailwayStation"),
    SHOPPING_COMPLEX("ShoppingComplex"),
    UNIVERSITY("University"),
    UNIT("Unit");

    private final String value;

    PremisesType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static PremisesType fromValue(String value) {
        for (PremisesType v : PremisesType.values()) {
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
