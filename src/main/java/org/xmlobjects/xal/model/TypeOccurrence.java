package org.xmlobjects.xal.model;

public enum TypeOccurrence {
    BEFORE("Before"),
    AFTER("After");

    private final String value;

    TypeOccurrence(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static TypeOccurrence fromValue(String value) {
        for (TypeOccurrence v : TypeOccurrence.values()) {
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
