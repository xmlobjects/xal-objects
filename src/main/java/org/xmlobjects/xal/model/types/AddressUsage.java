/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum AddressUsage {
    BUSINESS("Business"),
    BILLING("Billing"),
    COMMUNICATION("Communication"),
    CONTACT("Contact"),
    MAILING("Mailing"),
    PERSONAL("Personal"),
    POSTAL("Postal"),
    RESIDENTIAL("Residential");

    private final String value;

    AddressUsage(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static AddressUsage fromValue(String value) {
        for (AddressUsage v : AddressUsage.values()) {
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
