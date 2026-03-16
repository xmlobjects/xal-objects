/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;

import java.util.List;

public abstract class AbstractPremises extends AddressObject {
    private List<PremisesNameOrNumber> nameElementOrNumber;
    private String typeCode;

    public List<PremisesNameOrNumber> getNameElementOrNumber() {
        if (nameElementOrNumber == null)
            nameElementOrNumber = new ChildList<>(this);

        return nameElementOrNumber;
    }

    public boolean isSetNameElementOrNumber() {
        return nameElementOrNumber != null && !nameElementOrNumber.isEmpty();
    }

    public void setNameElementOrNumber(List<PremisesNameOrNumber> nameElementOrNumber) {
        this.nameElementOrNumber = asChild(nameElementOrNumber);
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
