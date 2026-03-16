/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
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
