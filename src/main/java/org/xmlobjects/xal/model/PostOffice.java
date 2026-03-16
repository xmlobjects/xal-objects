/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfPostOffice;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;
import java.util.List;

public class PostOffice extends AddressObject implements DataQuality {
    private List<Identifier> identifiers;
    private String type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public PostOffice() {
    }

    public PostOffice(String type) {
        this.type = type;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
    public DeprecatedPropertiesOfPostOffice getDeprecatedProperties() {
        return (DeprecatedPropertiesOfPostOffice) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfPostOffice();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
