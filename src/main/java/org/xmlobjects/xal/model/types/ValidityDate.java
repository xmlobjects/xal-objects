/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import java.time.OffsetDateTime;

public interface ValidityDate {
    OffsetDateTime getDateValidFrom();

    void setDateValidFrom(OffsetDateTime dateValidFrom);

    OffsetDateTime getDateValidTo();

    void setDateValidTo(OffsetDateTime dateValidTo);
}
