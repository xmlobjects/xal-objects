package org.xmlobjects.xal.model;

public enum RangeType {
    ODD("Odd"),
    EVEN("Even");

    private final String value;

    RangeType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static RangeType fromValue(String value) {
        for (RangeType v : RangeType.values()) {
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
