/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2021 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.AddressObject;
import org.xmlobjects.xal.model.deprecated.types.AddressIdentifier;
import org.xmlobjects.xal.model.deprecated.types.PostalServiceElement;
import org.xmlobjects.xal.visitor.XALVisitor;
import org.xmlobjects.xml.Attributes;

import java.util.List;

public class PostalServiceElements extends AddressObject {
    private List<AddressIdentifier> addressIdentifier;
    private PostalServiceElement endorsementLineCode;
    private PostalServiceElement keyLineCode;
    private PostalServiceElement barCode;
    private PostalServiceElement sortingCode;
    private PostalServiceElement addressLatitude;
    private PostalServiceElement addressLatitudeDirection;
    private PostalServiceElement addressLongitude;
    private PostalServiceElement addressLongitudeDirection;
    private List<PostalServiceElement> supplementaryPostalServiceData;
    private String type;
    private Attributes otherAttributes;

    public List<AddressIdentifier> getAddressIdentifier() {
        if (addressIdentifier == null)
            addressIdentifier = new ChildList<>(this);

        return addressIdentifier;
    }

    public void setAddressIdentifier(List<AddressIdentifier> addressIdentifier) {
        this.addressIdentifier = asChild(addressIdentifier);
    }

    public PostalServiceElement getEndorsementLineCode() {
        return endorsementLineCode;
    }

    public void setEndorsementLineCode(PostalServiceElement endorsementLineCode) {
        this.endorsementLineCode = asChild(endorsementLineCode);
    }

    public PostalServiceElement getKeyLineCode() {
        return keyLineCode;
    }

    public void setKeyLineCode(PostalServiceElement keyLineCode) {
        this.keyLineCode = asChild(keyLineCode);
    }

    public PostalServiceElement getBarCode() {
        return barCode;
    }

    public void setBarCode(PostalServiceElement barCode) {
        this.barCode = asChild(barCode);
    }

    public PostalServiceElement getSortingCode() {
        return sortingCode;
    }

    public void setSortingCode(PostalServiceElement sortingCode) {
        this.sortingCode = asChild(sortingCode);
    }

    public PostalServiceElement getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(PostalServiceElement addressLatitude) {
        this.addressLatitude = asChild(addressLatitude);
    }

    public PostalServiceElement getAddressLatitudeDirection() {
        return addressLatitudeDirection;
    }

    public void setAddressLatitudeDirection(PostalServiceElement addressLatitudeDirection) {
        this.addressLatitudeDirection = asChild(addressLatitudeDirection);
    }

    public PostalServiceElement getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(PostalServiceElement addressLongitude) {
        this.addressLongitude = asChild(addressLongitude);
    }

    public PostalServiceElement getAddressLongitudeDirection() {
        return addressLongitudeDirection;
    }

    public void setAddressLongitudeDirection(PostalServiceElement addressLongitudeDirection) {
        this.addressLongitudeDirection = asChild(addressLongitudeDirection);
    }

    public List<PostalServiceElement> getSupplementaryPostalServiceData() {
        if (supplementaryPostalServiceData == null)
            supplementaryPostalServiceData = new ChildList<>(this);

        return supplementaryPostalServiceData;
    }

    public void setSupplementaryPostalServiceData(List<PostalServiceElement> supplementaryPostalServiceData) {
        this.supplementaryPostalServiceData = asChild(supplementaryPostalServiceData);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Attributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new Attributes();

        return otherAttributes;
    }

    public void setOtherAttributes(Attributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
