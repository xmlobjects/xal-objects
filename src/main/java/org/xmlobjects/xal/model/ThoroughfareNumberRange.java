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

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThoroughfareNumberRange extends XALObject implements AddressObject, GrPostal {
    private List<AddressLine> addressLines;
    private ThoroughfareNumberFrom thoroughfareNumberFrom;
    private ThoroughfareNumberTo thoroughfareNumberTo;
    private RangeType rangeType;
    private String indicator;
    private String separator;
    private IndicatorOccurrence indicatorOccurrence;
    private NumberRangeOccurrence numberRangeOccurrence;
    private String type;
    private String code;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public ThoroughfareNumberFrom getThoroughfareNumberFrom() {
        return thoroughfareNumberFrom;
    }

    public void setThoroughfareNumberFrom(ThoroughfareNumberFrom thoroughfareNumberFrom) {
        this.thoroughfareNumberFrom = asChild(thoroughfareNumberFrom);
    }

    public ThoroughfareNumberTo getThoroughfareNumberTo() {
        return thoroughfareNumberTo;
    }

    public void setThoroughfareNumberTo(ThoroughfareNumberTo thoroughfareNumberTo) {
        this.thoroughfareNumberTo = asChild(thoroughfareNumberTo);
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
