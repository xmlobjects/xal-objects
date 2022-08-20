/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2022 Claus Nagel <claus.nagel@gmail.com>
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

import org.xmlobjects.xal.model.XALObject;

public class ThoroughfareNameOrNumber extends XALObject {
    private ThoroughfareName nameElement;
    private Identifier number;

    public ThoroughfareNameOrNumber() {
    }

    public ThoroughfareNameOrNumber(ThoroughfareName nameElement) {
        setNameElement(nameElement);
    }

    public ThoroughfareNameOrNumber(Identifier number) {
        setNumber(number);
    }

    public ThoroughfareName getNameElement() {
        return nameElement;
    }

    public boolean isSetNameElement() {
        return nameElement != null;
    }

    public void setNameElement(ThoroughfareName nameElement) {
        this.nameElement = asChild(nameElement);
        number = null;
    }

    public Identifier getNumber() {
        return number;
    }

    public boolean isSetNumber() {
        return number != null;
    }

    public void setNumber(Identifier number) {
        this.number = asChild(number);
        nameElement = null;
    }
}
