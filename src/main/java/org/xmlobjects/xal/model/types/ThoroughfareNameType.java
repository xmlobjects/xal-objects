/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum ThoroughfareNameType implements NameType {
    NAME_ONLY("NameOnly"),
    PRE_DIRECTION("PreDirection"),
    POST_DIRECTION("PostDirection"),
    NAME_AND_NUMBER("NameAndNumber"),
    NAME_AND_TYPE("NameAndType"),
    NAME_NUMBER_AND_TYPE("NameNumberAndType"),
    UNSTRUCTURED("Unstructured"),
    SUB_THOROUGHFARE_CONNECTOR("SubThoroughfareConnector"),
    REFERENCE_LOCATION("ReferenceLocation"),
    TYPE("Type");

    private final String value;

    ThoroughfareNameType(String value) {
        this.value = value;
    }

    @Override
    public String toValue() {
        return value;
    }

    public static ThoroughfareNameType fromValue(String value) {
        for (ThoroughfareNameType v : ThoroughfareNameType.values()) {
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
