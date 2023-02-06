/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2023 Claus Nagel <claus.nagel@gmail.com>
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

public enum LocalityType {
    MUNICIPALITY("Municipality"),
    POST_TOWN("PostTown"),
    PLACE("Place"),
    SUBURB("Suburb"),
    TOWN("Town"),
    VILLAGE("Village"),
    AREA("Area"),
    ZONE("Zone");

    private final String value;

    LocalityType(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static LocalityType fromValue(String value) {
        for (LocalityType v : LocalityType.values()) {
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
