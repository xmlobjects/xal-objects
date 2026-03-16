/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfAdministrativeArea;
import org.xmlobjects.xal.model.types.AdministrativeAreaName;
import org.xmlobjects.xal.model.types.AdministrativeAreaType;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;
import java.util.List;

public class AdministrativeArea extends AddressObject implements DataQuality {
    private List<AdministrativeAreaName> nameElements;
    private SubAdministrativeArea subAdministrativeArea;
    private AdministrativeAreaType type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public AdministrativeArea() {
    }

    public AdministrativeArea(AdministrativeAreaType type) {
        this.type = type;
    }

    public List<AdministrativeAreaName> getNameElements() {
        if (nameElements == null)
            nameElements = new ChildList<>(this);

        return nameElements;
    }

    public boolean isSetNameElements() {
        return nameElements != null && !nameElements.isEmpty();
    }

    public void setNameElements(List<AdministrativeAreaName> nameElements) {
        this.nameElements = asChild(nameElements);
    }

    public SubAdministrativeArea getSubAdministrativeArea() {
        return subAdministrativeArea;
    }

    public void setSubAdministrativeArea(SubAdministrativeArea subAdministrativeArea) {
        this.subAdministrativeArea = asChild(subAdministrativeArea);
    }

    public AdministrativeAreaType getType() {
        return type;
    }

    public void setType(AdministrativeAreaType type) {
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
    public DeprecatedPropertiesOfAdministrativeArea getDeprecatedProperties() {
        return (DeprecatedPropertiesOfAdministrativeArea) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfAdministrativeArea();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
