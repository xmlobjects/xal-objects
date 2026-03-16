/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubLocality;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.SubLocalityName;
import org.xmlobjects.xal.model.types.SubLocalityType;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;
import java.util.List;

public class SubLocality extends AddressObject implements DataQuality {
    private List<SubLocalityName> nameElements;
    private SubLocalityType type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public SubLocality() {
    }

    public SubLocality(SubLocalityType type) {
        this.type = type;
    }

    public List<SubLocalityName> getNameElements() {
        if (nameElements == null)
            nameElements = new ChildList<>(this);

        return nameElements;
    }

    public boolean isSetNameElements() {
        return nameElements != null && !nameElements.isEmpty();
    }

    public void setNameElements(List<SubLocalityName> nameElements) {
        this.nameElements = asChild(nameElements);
    }

    public SubLocalityType getType() {
        return type;
    }

    public void setType(SubLocalityType type) {
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
    public DeprecatedPropertiesOfSubLocality getDeprecatedProperties() {
        return (DeprecatedPropertiesOfSubLocality) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfSubLocality();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
