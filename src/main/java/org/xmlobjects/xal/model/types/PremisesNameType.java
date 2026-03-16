/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum PremisesNameType implements NameType {
    NAME("Name"),
    LOCATION("Location"),
    SUB_PREMISES_CONNECTOR("SubPremisesConnector"),
    INTERNAL_THOROUGHFARE("InternalThoroughfare"),
    REFERENCE_LOCATION("ReferenceLocation"),
    TYPE("Type");

    private final String value;

    PremisesNameType(String value) {
        this.value = value;
    }

    @Override
    public String toValue() {
        return value;
    }

    public static PremisesNameType fromValue(String value) {
        for (PremisesNameType v : PremisesNameType.values()) {
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
