/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfThoroughfare;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.util.List;

public class Thoroughfare extends AbstractThoroughfare {
    private List<SubThoroughfare> subThoroughfares;

    public List<SubThoroughfare> getSubThoroughfares() {
        if (subThoroughfares == null)
            subThoroughfares = new ChildList<>(this);

        return subThoroughfares;
    }

    public boolean isSetSubThoroughfares() {
        return subThoroughfares != null && !subThoroughfares.isEmpty();
    }

    public void setSubThoroughfares(List<SubThoroughfare> subThoroughfares) {
        this.subThoroughfares = asChild(subThoroughfares);
    }

    @Override
    public DeprecatedPropertiesOfThoroughfare getDeprecatedProperties() {
        return (DeprecatedPropertiesOfThoroughfare) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfThoroughfare();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
