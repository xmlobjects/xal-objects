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

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfPremises;
import org.xmlobjects.xal.model.types.PremisesType;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.util.List;

public class Premises extends AbstractPremises {
    private List<SubPremises> subPremises;
    private PremisesType type;

    public Premises() {
    }

    public Premises(PremisesType type) {
        this.type = type;
    }

    public List<SubPremises> getSubPremises() {
        if (subPremises == null)
            subPremises = new ChildList<>(this);

        return subPremises;
    }

    public boolean isSetSubPremises() {
        return subPremises != null && !subPremises.isEmpty();
    }

    public void setSubPremises(List<SubPremises> subPremises) {
        this.subPremises = asChild(subPremises);
    }

    public PremisesType getType() {
        return type;
    }

    public void setType(PremisesType type) {
        this.type = type;
    }

    @Override
    public DeprecatedPropertiesOfPremises getDeprecatedProperties() {
        return (DeprecatedPropertiesOfPremises) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfPremises();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
