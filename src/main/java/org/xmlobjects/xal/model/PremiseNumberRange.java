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

package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public class PremiseNumberRange extends XALObject implements AddressObject {
    private PremiseNumberRangeFrom premiseNumberRangeFrom;
    private PremiseNumberRangeTo premiseNumberRangeTo;
    private String rangeType;
    private String indicator;
    private String separator;
    private String type;
    private IndicatorOccurrence indicatorOccurrence;
    private NumberRangeOccurrence numberRangeOccurrence;

    public PremiseNumberRangeFrom getPremiseNumberRangeFrom() {
        return premiseNumberRangeFrom;
    }

    public void setPremiseNumberRangeFrom(PremiseNumberRangeFrom premiseNumberRangeFrom) {
        this.premiseNumberRangeFrom = asChild(premiseNumberRangeFrom);
    }

    public PremiseNumberRangeTo getPremiseNumberRangeTo() {
        return premiseNumberRangeTo;
    }

    public void setPremiseNumberRangeTo(PremiseNumberRangeTo premiseNumberRangeTo) {
        this.premiseNumberRangeTo = asChild(premiseNumberRangeTo);
    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
        this.rangeType = rangeType;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IndicatorOccurrence getIndicatorOccurrence() {
        return indicatorOccurrence;
    }

    public void setIndicatorOccurrence(IndicatorOccurrence indicatorOccurrence) {
        this.indicatorOccurrence = indicatorOccurrence;
    }

    public NumberRangeOccurrence getNumberRangeOccurrence() {
        return numberRangeOccurrence;
    }

    public void setNumberRangeOccurrence(NumberRangeOccurrence numberRangeOccurrence) {
        this.numberRangeOccurrence = numberRangeOccurrence;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
