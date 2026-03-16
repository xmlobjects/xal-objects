/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.ThoroughfareNameOrNumber;

import java.time.OffsetDateTime;
import java.util.List;

public abstract class AbstractThoroughfare extends AddressObject implements DataQuality {
    private List<ThoroughfareNameOrNumber> nameElementOrNumber;
    private String type;
    private String typeCode;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public List<ThoroughfareNameOrNumber> getNameElementOrNumber() {
        if (nameElementOrNumber == null)
            nameElementOrNumber = new ChildList<>(this);

        return nameElementOrNumber;
    }

    public boolean isSetNameElementOrNumber() {
        return nameElementOrNumber != null && !nameElementOrNumber.isEmpty();
    }

    public void setNameElementOrNumber(List<ThoroughfareNameOrNumber> nameElementOrNumber) {
        this.nameElementOrNumber = asChild(nameElementOrNumber);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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
}
