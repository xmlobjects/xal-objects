/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfCountry;
import org.xmlobjects.xal.model.types.CountryName;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.util.List;

public class Country extends AddressObject {
    private List<CountryName> nameElements;

    public List<CountryName> getNameElements() {
        if (nameElements == null)
            nameElements = new ChildList<>(this);

        return nameElements;
    }

    public boolean isSetNameElements() {
        return nameElements != null && !nameElements.isEmpty();
    }

    public void setNameElements(List<CountryName> nameElements) {
        this.nameElements = asChild(nameElements);
    }

    @Override
    public DeprecatedPropertiesOfCountry getDeprecatedProperties() {
        return (DeprecatedPropertiesOfCountry) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfCountry();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
