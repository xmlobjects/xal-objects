/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.deprecated.types.AddressIdentifier;
import org.xmlobjects.xal.model.deprecated.types.PostalServiceElement;
import org.xmlobjects.xal.visitor.XALVisitor;

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

    public List<AddressIdentifier> getAddressIdentifiers() {
        if (addressIdentifier == null)
            addressIdentifier = new ChildList<>(this);

        return addressIdentifier;
    }

    public boolean isSetAddressIdentifiers() {
        return addressIdentifier != null && !addressIdentifier.isEmpty();
    }

    public void setAddressIdentifiers(List<AddressIdentifier> addressIdentifier) {
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

    public boolean isSetSupplementaryPostalServiceData() {
        return supplementaryPostalServiceData != null && !supplementaryPostalServiceData.isEmpty();
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

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
