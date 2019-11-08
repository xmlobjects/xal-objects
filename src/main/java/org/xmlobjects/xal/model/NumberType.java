package org.xmlobjects.xal.model;

public enum NumberType {
    SINGLE("Single"),
    RANGE("Range");

    private final String value;

    NumberType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static NumberType fromValue(String value) {
        for (NumberType v : NumberType.values()) {
            if (v.value.equals(value))
                return v;
        }

        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
