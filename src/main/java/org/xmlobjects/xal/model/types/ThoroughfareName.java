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

public class ThoroughfareName extends Name<ThoroughfareNameType> {

    public ThoroughfareName() {
        super();
    }

    public ThoroughfareName(String content, ThoroughfareNameType nameType) {
        super(content, nameType);
    }

    public ThoroughfareName(String content) {
        super(content);
    }

    public ThoroughfareName(ThoroughfareNameType nameType) {
        super(nameType);
    }
}
