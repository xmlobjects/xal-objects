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

public class DependentThoroughfare extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private ThoroughfarePreDirection thoroughfarePreDirection;
    private ThoroughfareLeadingType thoroughfareLeadingType;
    private List<ThoroughfareName> thoroughfareNames;
    private ThoroughfareTrailingType thoroughfareTrailingType;
    private ThoroughfarePostDirection thoroughfarePostDirection;
    private List<GenericElement> genericElements;
    private String type;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public ThoroughfarePreDirection getThoroughfarePreDirection() {
        return thoroughfarePreDirection;
    }

    public void setThoroughfarePreDirection(ThoroughfarePreDirection thoroughfarePreDirection) {
        this.thoroughfarePreDirection = asChild(thoroughfarePreDirection);
    }

    public ThoroughfareLeadingType getThoroughfareLeadingType() {
        return thoroughfareLeadingType;
    }

    public void setThoroughfareLeadingType(ThoroughfareLeadingType thoroughfareLeadingType) {
        this.thoroughfareLeadingType = asChild(thoroughfareLeadingType);
    }

    public List<ThoroughfareName> getThoroughfareNames() {
        return thoroughfareNames;
    }

    public void setThoroughfareNames(List<ThoroughfareName> thoroughfareNames) {
        this.thoroughfareNames = asChild(thoroughfareNames);
    }

    public ThoroughfareTrailingType getThoroughfareTrailingType() {
        return thoroughfareTrailingType;
    }

    public void setThoroughfareTrailingType(ThoroughfareTrailingType thoroughfareTrailingType) {
        this.thoroughfareTrailingType = asChild(thoroughfareTrailingType);
    }

    public ThoroughfarePostDirection getThoroughfarePostDirection() {
        return thoroughfarePostDirection;
    }

    public void setThoroughfarePostDirection(ThoroughfarePostDirection thoroughfarePostDirection) {
        this.thoroughfarePostDirection = asChild(thoroughfarePostDirection);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
