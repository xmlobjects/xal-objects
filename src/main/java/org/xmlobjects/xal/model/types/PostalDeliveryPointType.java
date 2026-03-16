/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum PostalDeliveryPointType {
    GPO_BOX("GPOBox"),
    PO_BOX("POBox"),
    LOCKED_BAG("LockedBag"),
    MAIL_STOP("MailStop"),
    PIGEON_HOLE("PigeonHole"),
    PRIVATE_BAG("PrivateBag");

    private final String value;

    PostalDeliveryPointType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static PostalDeliveryPointType fromValue(String value) {
        for (PostalDeliveryPointType v : PostalDeliveryPointType.values()) {
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
