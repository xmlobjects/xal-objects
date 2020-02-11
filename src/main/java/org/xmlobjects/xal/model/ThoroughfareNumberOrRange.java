/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.model;

public class ThoroughfareNumberOrRange extends XALObject {
    private ThoroughfareNumber thoroughfareNumber;
    private ThoroughfareNumberRange thoroughfareNumberRange;

    public ThoroughfareNumberOrRange() {
    }

    public ThoroughfareNumberOrRange(ThoroughfareNumber thoroughfareNumber) {
        setThoroughfareNumber(thoroughfareNumber);
    }

    public ThoroughfareNumberOrRange(ThoroughfareNumberRange thoroughfareNumberRange) {
        setThoroughfareNumberRange(thoroughfareNumberRange);
    }

    public ThoroughfareNumber getThoroughfareNumber() {
        return thoroughfareNumber;
    }

    public boolean isSetThoroughfareNumber() {
        return thoroughfareNumber != null;
    }

    public void setThoroughfareNumber(ThoroughfareNumber thoroughfareNumber) {
        clearChoice();
        this.thoroughfareNumber = asChild(thoroughfareNumber);
    }

    public ThoroughfareNumberRange getThoroughfareNumberRange() {
        return thoroughfareNumberRange;
    }

    public boolean isSetThoroughfareNumberRange() {
        return thoroughfareNumberRange != null;
    }

    public void setThoroughfareNumberRange(ThoroughfareNumberRange thoroughfareNumberRange) {
        clearChoice();
        this.thoroughfareNumberRange = asChild(thoroughfareNumberRange);
    }

    private void clearChoice() {
        thoroughfareNumber = null;
        thoroughfareNumberRange = null;
    }
}
