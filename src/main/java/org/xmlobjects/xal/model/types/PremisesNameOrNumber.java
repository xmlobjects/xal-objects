/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.XALObject;

public class PremisesNameOrNumber extends XALObject {
    private PremisesName nameElement;
    private Identifier number;

    public PremisesNameOrNumber() {
    }

    public PremisesNameOrNumber(PremisesName nameElement) {
        setNameElement(nameElement);
    }

    public PremisesNameOrNumber(Identifier number) {
        setNumber(number);
    }

    public PremisesName getNameElement() {
        return nameElement;
    }

    public boolean isSetNameElement() {
        return nameElement != null;
    }

    public void setNameElement(PremisesName nameElement) {
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
