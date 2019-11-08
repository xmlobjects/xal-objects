package org.xmlobjects.xal.model;

public enum NumberRangeOccurrence {
    BEFORE_NAME("BeforeName"),
    AFTER_NAME("AfterName"),
    BEFORE_TYPE("BeforeType"),
    AFTER_TYPE("AfterType");

    private final String value;

    NumberRangeOccurrence(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static NumberRangeOccurrence fromValue(String value) {
        for (NumberRangeOccurrence v : NumberRangeOccurrence.values()) {
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
