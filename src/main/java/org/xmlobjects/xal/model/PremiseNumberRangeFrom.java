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

import java.util.List;

public class PremiseNumberRangeFrom extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private List<PremiseNumberPrefix> premiseNumberPrefixes;
    private List<PremiseNumber> premiseNumbers;
    private List<PremiseNumberSuffix> premiseNumberSuffixes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<PremiseNumberPrefix> getPremiseNumberPrefixes() {
        if (premiseNumberPrefixes == null)
            premiseNumberPrefixes = new ChildList<>(this);

        return premiseNumberPrefixes;
    }

    public void setPremiseNumberPrefixes(List<PremiseNumberPrefix> premiseNumberPrefixes) {
        this.premiseNumberPrefixes = asChild(premiseNumberPrefixes);
    }

    public List<PremiseNumber> getPremiseNumbers() {
        if (premiseNumbers == null)
            premiseNumbers = new ChildList<>(this);

        return premiseNumbers;
    }

    public void setPremiseNumbers(List<PremiseNumber> premiseNumbers) {
        this.premiseNumbers = asChild(premiseNumbers);
    }

    public List<PremiseNumberSuffix> getPremiseNumberSuffixes() {
        if (premiseNumberSuffixes == null)
            premiseNumberSuffixes = new ChildList<>(this);

        return premiseNumberSuffixes;
    }

    public void setPremiseNumberSuffixes(List<PremiseNumberSuffix> premiseNumberSuffixes) {
        this.premiseNumberSuffixes = asChild(premiseNumberSuffixes);
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
