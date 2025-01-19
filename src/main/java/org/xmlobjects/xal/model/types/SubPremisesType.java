/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2025 Claus Nagel <claus.nagel@gmail.com>
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

public enum SubPremisesType {
    ROOM("Room"),
    SUITE("Suite"),
    APARTMENT("Apartment"),
    SHOP("Shop"),
    OFFICE("Office"),
    UNIT("Unit");

    private final String value;

    SubPremisesType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static SubPremisesType fromValue(String value) {
        for (SubPremisesType v : SubPremisesType.values()) {
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
