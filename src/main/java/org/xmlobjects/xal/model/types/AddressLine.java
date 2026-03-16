/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.XALObject;

import java.time.OffsetDateTime;

public class AddressLine extends XALObject implements DataQuality {
    private String content;
    private String type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private GenericAttributes otherAttributes;

    public AddressLine() {
    }

    public AddressLine(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public AddressLine(String content) {
        this(content, null);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public GenericAttributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new GenericAttributes();

        return otherAttributes;
    }

    public boolean isSetOtherAttributes() {
        return otherAttributes != null && !otherAttributes.isEmpty();
    }

    public void setOtherAttributes(GenericAttributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
