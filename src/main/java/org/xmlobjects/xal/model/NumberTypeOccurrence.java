package org.xmlobjects.xal.model;

public enum NumberTypeOccurrence {
    BEFORE("Before"),
    AFTER("After");

    private final String value;

    NumberTypeOccurrence(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static NumberTypeOccurrence fromValue(String value) {
        for (NumberTypeOccurrence v : NumberTypeOccurrence.values()) {
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
