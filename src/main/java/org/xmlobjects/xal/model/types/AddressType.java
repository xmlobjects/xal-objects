/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public enum AddressType {
    AIRPOR("Airport"),
    BUSINESS("Business"),
    CARAVAN_PARK("CaravanPark"),
    COMMERCIAL_PARK("CommercialPark"),
    COMMUNITY_DEVELOPMENT("CommunityDevelopment"),
    EDUCATIONAL_INSTITUTION("EducationalInstitution"),
    ENTERTAINMENT("Entertainment"),
    HOSPITAL("Hospital"),
    LOCATION("Location"),
    MARINA("Marina"),
    MILITARY_BASE("MilitaryBase"),
    OVERSEAS_MILITARY("OverseasMilitary"),
    PORT("Port"),
    PRIMARY("Primary"),
    RECREATIONAL_PARK("RecreationalPark"),
    RESORT("Resort"),
    RETIREMENT_VILLAGE("RetirementVillage"),
    RURAL("Rural"),
    SECONDARY("Secondary"),
    SHOPPING_CENTRE("ShoppingCentre"),
    SPORTING_CENTRE("SportingCentre"),
    URBAN("Urban");

    private final String value;

    AddressType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static AddressType fromValue(String value) {
        for (AddressType v : AddressType.values()) {
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
