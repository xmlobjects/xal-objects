/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.types.Identifier;

import java.util.List;

public class DeprecatedPropertiesOfPremises extends DeprecatedProperties {
    private List<Identifier> buildingNames;
    private PostalDeliveryPoint postBox;
    private Thoroughfare thoroughfare;
    private PostalDeliveryPoint mailStop;
    private PostCode postalCode;
    private Premises firm;
    private Premises premise;
    private SubPremises department;

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

    public PostalDeliveryPoint getPostBox() {
        return postBox;
    }

    public void setPostBox(PostalDeliveryPoint postBox) {
        this.postBox = asChild(postBox);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        this.thoroughfare = asChild(thoroughfare);
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

    public Premises getPremise() {
        return premise;
    }

    public void setPremise(Premises premise) {
        this.premise = asChild(premise);
    }

    public SubPremises getDepartment() {
        return department;
    }

    public void setDepartment(SubPremises department) {
        this.department = asChild(department);
    }
}
