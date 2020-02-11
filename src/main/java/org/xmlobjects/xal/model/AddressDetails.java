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

public class AddressDetails extends XALObject implements AddressObject, GrPostal {
    private PostalServiceElements postalServiceElements;
    private Address address;
    private AddressLines addressLines;
    private Country country;
    private AdministrativeArea administrativeArea;
    private Locality locality;
    private Thoroughfare thoroughfare;
    private List<GenericElement> genericElements;
    private String addressType;
    private String currentStatus;
    private String validFromDate;
    private String validToDate;
    private String usage;
    private String code;
    private String addressDetailsKey;
    private Map<QName, String> otherAttributes;

    public PostalServiceElements getPostalServiceElements() {
        return postalServiceElements;
    }

    public void setPostalServiceElements(PostalServiceElements postalServiceElements) {
        this.postalServiceElements = asChild(postalServiceElements);
    }

    public Address getAddress() {
        return address;
    }

    public boolean isSetAddress() {
        return address != null;
    }

    public void setAddress(Address address) {
        clearChoice();
        this.address = asChild(address);
    }

    public AddressLines getAddressLines() {
        return addressLines;
    }

    public boolean isSetAddressLines() {
        return addressLines != null;
    }

    public void setAddressLines(AddressLines addressLines) {
        clearChoice();
        this.addressLines = asChild(addressLines);
    }

    public Country getCountry() {
        return country;
    }

    public boolean isSetCountry() {
        return country != null;
    }

    public void setCountry(Country country) {
        clearChoice();
        this.country = asChild(country);
    }

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public boolean isSetAdministrativeArea() {
        return administrativeArea != null;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        clearChoice();
        this.administrativeArea = asChild(administrativeArea);
    }

    public Locality getLocality() {
        return locality;
    }

    public boolean isSetLocality() {
        return locality != null;
    }

    public void setLocality(Locality locality) {
        clearChoice();
        this.locality = asChild(locality);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public boolean isSetThoroughfare() {
        return thoroughfare != null;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        clearChoice();
        this.thoroughfare = asChild(thoroughfare);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(String validFromDate) {
        this.validFromDate = validFromDate;
    }

    public String getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(String validToDate) {
        this.validToDate = validToDate;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getAddressDetailsKey() {
        return addressDetailsKey;
    }

    public void setAddressDetailsKey(String addressDetailsKey) {
        this.addressDetailsKey = addressDetailsKey;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    private void clearChoice() {
        address = null;
        addressLines = null;
        country = null;
        administrativeArea = null;
        locality = null;
        thoroughfare = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
