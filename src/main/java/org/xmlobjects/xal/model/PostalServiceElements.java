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

public class PostalServiceElements extends XALObject implements AddressObject {
    private List<AddressIdentifier> addressIdentifier;
    private EndorsementLineCode endorsementLineCode;
    private KeyLineCode keyLineCode;
    private Barcode barcode;
    private SortingCode sortingCode;
    private AddressLatitude addressLatitude;
    private AddressLatitudeDirection addressLatitudeDirection;
    private AddressLongitude addressLongitude;
    private AddressLongitudeDirection addressLongitudeDirection;
    private List<SupplementaryPostalServiceData> supplementaryPostalServiceData;
    private List<GenericElement> genericElements;
    private String type;
    private Map<QName, String> otherAttributes;

    public List<AddressIdentifier> getAddressIdentifier() {
        if (addressIdentifier == null)
            addressIdentifier = new ChildList<>(this);

        return addressIdentifier;
    }

    public void setAddressIdentifier(List<AddressIdentifier> addressIdentifier) {
        this.addressIdentifier = asChild(addressIdentifier);
    }

    public EndorsementLineCode getEndorsementLineCode() {
        return endorsementLineCode;
    }

    public void setEndorsementLineCode(EndorsementLineCode endorsementLineCode) {
        this.endorsementLineCode = asChild(endorsementLineCode);
    }

    public KeyLineCode getKeyLineCode() {
        return keyLineCode;
    }

    public void setKeyLineCode(KeyLineCode keyLineCode) {
        this.keyLineCode = asChild(keyLineCode);
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = asChild(barcode);
    }

    public SortingCode getSortingCode() {
        return sortingCode;
    }

    public void setSortingCode(SortingCode sortingCode) {
        this.sortingCode = asChild(sortingCode);
    }

    public AddressLatitude getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(AddressLatitude addressLatitude) {
        this.addressLatitude = asChild(addressLatitude);
    }

    public AddressLatitudeDirection getAddressLatitudeDirection() {
        return addressLatitudeDirection;
    }

    public void setAddressLatitudeDirection(AddressLatitudeDirection addressLatitudeDirection) {
        this.addressLatitudeDirection = asChild(addressLatitudeDirection);
    }

    public AddressLongitude getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(AddressLongitude addressLongitude) {
        this.addressLongitude = asChild(addressLongitude);
    }

    public AddressLongitudeDirection getAddressLongitudeDirection() {
        return addressLongitudeDirection;
    }

    public void setAddressLongitudeDirection(AddressLongitudeDirection addressLongitudeDirection) {
        this.addressLongitudeDirection = asChild(addressLongitudeDirection);
    }

    public List<SupplementaryPostalServiceData> getSupplementaryPostalServiceData() {
        if (supplementaryPostalServiceData == null)
            supplementaryPostalServiceData = new ChildList<>(this);

        return supplementaryPostalServiceData;
    }

    public void setSupplementaryPostalServiceData(List<SupplementaryPostalServiceData> supplementaryPostalServiceData) {
        this.supplementaryPostalServiceData = asChild(supplementaryPostalServiceData);
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
