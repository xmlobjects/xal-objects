/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfLocality;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.LocalityName;
import org.xmlobjects.xal.model.types.LocalityType;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;
import java.util.List;

public class Locality extends AddressObject implements DataQuality {
    private List<LocalityName> nameElements;
    private SubLocality subLocality;
    private LocalityType type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public Locality() {
    }

    public Locality(LocalityType type) {
        this.type = type;
    }

    public List<LocalityName> getNameElements() {
        if (nameElements == null)
            nameElements = new ChildList<>(this);

        return nameElements;
    }

    public boolean isSetNameElements() {
        return nameElements != null && !nameElements.isEmpty();
    }

    public void setNameElements(List<LocalityName> nameElements) {
        this.nameElements = asChild(nameElements);
    }

    public SubLocality getSubLocality() {
        return subLocality;
    }

    public void setSubLocality(SubLocality subLocality) {
        this.subLocality = asChild(subLocality);
    }

    public LocalityType getType() {
        return type;
    }

    public void setType(LocalityType type) {
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
    public DeprecatedPropertiesOfLocality getDeprecatedProperties() {
        return (DeprecatedPropertiesOfLocality) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfLocality();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
