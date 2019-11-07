package org.xmlobjects.xal.model;

public enum NameNumberOccurrence {
    BEFORE("Before"),
    AFTER("After");

    private final String value;

    NameNumberOccurrence(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static NameNumberOccurrence fromValue(String value) {
        for (NameNumberOccurrence v : NameNumberOccurrence.values()) {
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
