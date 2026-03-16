/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfPostCode;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;
import java.util.List;

public class PostCode extends AddressObject implements DataQuality {
    private List<Identifier> identifiers;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public List<Identifier> getIdentifiers() {
        if (identifiers == null)
            identifiers = new ChildList<>(this);

        return identifiers;
    }

    public boolean isSetIdentifiers() {
        return identifiers != null && !identifiers.isEmpty();
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = asChild(identifiers);
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
    public DeprecatedPropertiesOfPostCode getDeprecatedProperties() {
        return (DeprecatedPropertiesOfPostCode) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfPostCode();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
