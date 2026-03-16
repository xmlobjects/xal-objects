/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import java.time.OffsetDateTime;

public interface DataQuality {
    DataQualityType getDataQualityType();

    void setDataQualityType(DataQualityType dataQualityType);

    OffsetDateTime getValidFrom();

    void setValidFrom(OffsetDateTime validFrom);

    OffsetDateTime getValidTo();

    void setValidTo(OffsetDateTime validTo);
}
