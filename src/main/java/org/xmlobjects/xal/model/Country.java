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
