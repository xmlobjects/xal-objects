/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
