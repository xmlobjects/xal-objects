/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.SubPremises;
import org.xmlobjects.xal.model.types.Identifier;

import java.util.List;

public class DeprecatedPropertiesOfSubPremises extends DeprecatedProperties {
    private List<Identifier> buildingNames;
    private PostalDeliveryPoint mailStop;
    private PostCode postalCode;
    private Premises firm;
    private SubPremises subPremise;

    public List<Identifier> getBuildingNames() {
        if (buildingNames == null)
            buildingNames = new ChildList<>(this);

        return buildingNames;
    }

    public boolean isSetBuildingNames() {
        return buildingNames != null && !buildingNames.isEmpty();
    }

    public void setBuildingNames(List<Identifier> buildingNames) {
        this.buildingNames = asChild(buildingNames);
    }

    public PostalDeliveryPoint getMailStop() {
        return mailStop;
    }

    public void setMailStop(PostalDeliveryPoint mailStop) {
        this.mailStop = asChild(mailStop);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }

    public Premises getFirm() {
        return firm;
    }

    public void setFirm(Premises firm) {
        this.firm = asChild(firm);
    }

    public SubPremises getSubPremise() {
        return subPremise;
    }

    public void setSubPremise(SubPremises subPremise) {
        this.subPremise = asChild(subPremise);
    }
}
