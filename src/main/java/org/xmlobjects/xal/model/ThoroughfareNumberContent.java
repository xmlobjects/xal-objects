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

public class ThoroughfareNumberContent extends XALObject {
    private AddressLine addressLine;
    private ThoroughfareNumber thoroughfareNumber;
    private ThoroughfareNumberPrefix thoroughfareNumberPrefix;
    private ThoroughfareNumberSuffix thoroughfareNumberSuffix;
    private String string;

    public ThoroughfareNumberContent() {
    }

    public ThoroughfareNumberContent(AddressLine addressLine) {
        setAddressLine(addressLine);
    }

    public ThoroughfareNumberContent(ThoroughfareNumber thoroughfareNumber) {
        setThoroughfareNumber(thoroughfareNumber);
    }

    public ThoroughfareNumberContent(ThoroughfareNumberPrefix thoroughfareNumberPrefix) {
        setThoroughfareNumberPrefix(thoroughfareNumberPrefix);
    }

    public ThoroughfareNumberContent(ThoroughfareNumberSuffix thoroughfareNumberSuffix) {
        setThoroughfareNumberSuffix(thoroughfareNumberSuffix);
    }

    public ThoroughfareNumberContent(String string) {
        this.string = string;
    }

    public AddressLine getAddressLine() {
        return addressLine;
    }

    public boolean isSetAddressLine() {
        return addressLine != null;
    }

    public void setAddressLine(AddressLine addressLine) {
        clearChoice();
        this.addressLine = asChild(addressLine);
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

    public ThoroughfareNumberPrefix getThoroughfareNumberPrefix() {
        return thoroughfareNumberPrefix;
    }

    public boolean isSetThoroughfareNumberPrefix() {
        return thoroughfareNumberPrefix != null;
    }

    public void setThoroughfareNumberPrefix(ThoroughfareNumberPrefix thoroughfareNumberPrefix) {
        clearChoice();
        this.thoroughfareNumberPrefix = asChild(thoroughfareNumberPrefix);
    }

    public ThoroughfareNumberSuffix getThoroughfareNumberSuffix() {
        return thoroughfareNumberSuffix;
    }

    public boolean isSetThoroughfareNumberSuffix() {
        return thoroughfareNumberSuffix != null;
    }

    public void setThoroughfareNumberSuffix(ThoroughfareNumberSuffix thoroughfareNumberSuffix) {
        clearChoice();
        this.thoroughfareNumberSuffix = asChild(thoroughfareNumberSuffix);
    }

    public String getString() {
        return string;
    }

    public boolean isSetString() {
        return string != null;
    }

    public void setString(String string) {
        clearChoice();
        this.string = string;
    }

    private void clearChoice() {
        addressLine = null;
        thoroughfareNumber = null;
        thoroughfareNumberPrefix = null;
        thoroughfareNumberSuffix = null;
        string = null;
    }
}
