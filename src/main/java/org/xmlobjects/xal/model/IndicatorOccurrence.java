package org.xmlobjects.xal.model;

public enum IndicatorOccurrence {
    BEFORE("Before"),
    AFTER("After");

    private final String value;

    IndicatorOccurrence(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static IndicatorOccurrence fromValue(String value) {
        for (IndicatorOccurrence v : IndicatorOccurrence.values()) {
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
