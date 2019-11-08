package org.xmlobjects.xal.model;

public enum NumberOccurrence {
    BEFORE_NAME("BeforeName"),
    AFTER_NAME("AfterName"),
    BEFORE_TYPE("BeforeType"),
    AFTER_TYPE("AfterType");

    private final String value;

    NumberOccurrence(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static NumberOccurrence fromValue(String value) {
        for (NumberOccurrence v : NumberOccurrence.values()) {
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
