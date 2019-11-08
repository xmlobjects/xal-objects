package org.xmlobjects.xal.model;

public enum DependentThoroughfares {
    YES("Yes"),
    NO("No");

    private final String value;

    DependentThoroughfares(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static DependentThoroughfares fromValue(String value) {
        for (DependentThoroughfares v : DependentThoroughfares.values()) {
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
