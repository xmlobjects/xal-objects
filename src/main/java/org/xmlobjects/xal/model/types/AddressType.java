/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2024 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
