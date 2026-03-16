/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
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
