/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubAdministrativeArea;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaName;
import org.xmlobjects.xal.model.types.SubAdministrativeAreaType;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;
import java.util.List;

public class SubAdministrativeArea extends AddressObject implements DataQuality {
    private List<SubAdministrativeAreaName> nameElements;
    private SubAdministrativeAreaType type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public SubAdministrativeArea() {
    }

    public SubAdministrativeArea(SubAdministrativeAreaType type) {
        this.type = type;
    }

    public List<SubAdministrativeAreaName> getNameElements() {
        if (nameElements == null)
            nameElements = new ChildList<>(this);

        return nameElements;
    }

    public boolean isSetNameElements() {
        return nameElements != null && !nameElements.isEmpty();
    }

    public void setNameElements(List<SubAdministrativeAreaName> nameElements) {
        this.nameElements = asChild(nameElements);
    }

    public SubAdministrativeAreaType getType() {
        return type;
    }

    public void setType(SubAdministrativeAreaType type) {
        this.type = type;
    }

    @Override
    public DataQualityType getDataQualityType() {
        return dataQualityType;
    }

    @Override
    public void setDataQualityType(DataQualityType dataQualityType) {
        this.dataQualityType = dataQualityType;
    }

    @Override
    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public OffsetDateTime getValidTo() {
        return validTo;
    }

    @Override
    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    @Override
    public DeprecatedPropertiesOfSubAdministrativeArea getDeprecatedProperties() {
        return (DeprecatedPropertiesOfSubAdministrativeArea) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfSubAdministrativeArea();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
