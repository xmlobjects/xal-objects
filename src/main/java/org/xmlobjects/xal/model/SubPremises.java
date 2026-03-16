/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubPremises;
import org.xmlobjects.xal.model.types.SubPremisesType;
import org.xmlobjects.xal.visitor.XALVisitor;

public class SubPremises extends AbstractPremises {
    private SubPremisesType type;

    public SubPremises() {
    }

    public SubPremises(SubPremisesType type) {
        this.type = type;
    }

    public SubPremisesType getType() {
        return type;
    }

    public void setType(SubPremisesType type) {
        this.type = type;
    }

    @Override
    public DeprecatedPropertiesOfSubPremises getDeprecatedProperties() {
        return (DeprecatedPropertiesOfSubPremises) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfSubPremises();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
