/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2023 Claus Nagel <claus.nagel@gmail.com>
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
