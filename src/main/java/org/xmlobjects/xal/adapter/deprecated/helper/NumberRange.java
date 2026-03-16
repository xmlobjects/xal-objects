/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.helper;

import org.xmlobjects.xal.model.types.Identifier;

public class NumberRange {
    private final ParsedNumber rangeFrom;
    private ParsedNumber rangeTo;
    private Identifier separator;

    NumberRange(ParsedNumber rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public ParsedNumber getRangeFrom() {
        return rangeFrom;
    }

    public ParsedNumber getRangeTo() {
        return rangeTo;
    }

    void setRangeTo(ParsedNumber rangeTo) {
        this.rangeTo = rangeTo;
    }

    public Identifier getSeparator() {
        return separator;
    }

    void setSeparator(Identifier separator) {
        this.separator = separator;
    }
}
