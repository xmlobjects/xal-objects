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

import org.xmlobjects.xal.model.types.AddressType;
import org.xmlobjects.xal.model.types.AddressUsage;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.LanguageCode;
import org.xmlobjects.xal.model.types.ValidityDate;

import javax.xml.namespace.QName;
import java.time.OffsetDateTime;
import java.util.Map;

public class Address extends XALObject implements DataQuality, ValidityDate, LanguageCode {
    private FreeTextAddress freeTextAddress;
    private Country country;
    private AdministrativeArea administrativeArea;
    private Locality locality;
    private Thoroughfare thoroughfare;
    private Premises premises;
    private PostCode postCode;
    private RuralDelivery ruralDelivery;
    private PostalDeliveryPoint postalDeliveryPoint;
    private PostOffice postOffice;
    private GeoRSS geoRSS;
    private LocationByCoordinates locationByCoordinates;
    private AddressType type;
    private String addressId;
    private String addressIdType;
    private String id;
    private AddressUsage usage;
    private String deliveryMode;
    private String status;
    private String addressKey;
    private String addressKeyRef;
    private String xlinkType;
    private String xlinkLabel;
    private String xlinkHRef;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private OffsetDateTime dateValidFrom;
    private OffsetDateTime dateValidTo;
    private String languageCode;
    private Map<QName, String> otherAttributes;

    public FreeTextAddress getFreeTextAddress() {
        return freeTextAddress;
    }

    public void setFreeTextAddress(FreeTextAddress freeTextAddress) {
        this.freeTextAddress = asChild(freeTextAddress);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = asChild(country);
    }

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        this.administrativeArea = asChild(administrativeArea);
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = asChild(locality);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        this.thoroughfare = asChild(thoroughfare);
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = asChild(premises);
    }

    public PostCode getPostCode() {
        return postCode;
    }

    public void setPostCode(PostCode postCode) {
        this.postCode = asChild(postCode);
    }

    public RuralDelivery getRuralDelivery() {
        return ruralDelivery;
    }

    public void setRuralDelivery(RuralDelivery ruralDelivery) {
        this.ruralDelivery = asChild(ruralDelivery);
    }

    public PostalDeliveryPoint getPostalDeliveryPoint() {
        return postalDeliveryPoint;
    }

    public void setPostalDeliveryPoint(PostalDeliveryPoint postalDeliveryPoint) {
        this.postalDeliveryPoint = asChild(postalDeliveryPoint);
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = asChild(postOffice);
    }

    public GeoRSS getGeoRSS() {
        return geoRSS;
    }

    public void setGeoRSS(GeoRSS geoRSS) {
        this.geoRSS = asChild(geoRSS);
    }

    public LocationByCoordinates getLocationByCoordinates() {
        return locationByCoordinates;
    }

    public void setLocationByCoordinates(LocationByCoordinates locationByCoordinates) {
        this.locationByCoordinates = asChild(locationByCoordinates);
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressIdType() {
        return addressIdType;
    }

    public void setAddressIdType(String addressIdType) {
        this.addressIdType = addressIdType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressUsage getUsage() {
        return usage;
    }

    public void setUsage(AddressUsage usage) {
        this.usage = usage;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(String addressKey) {
        this.addressKey = addressKey;
    }

    public String getAddressKeyRef() {
        return addressKeyRef;
    }

    public void setAddressKeyRef(String addressKeyRef) {
        this.addressKeyRef = addressKeyRef;
    }

    public String getXlinkType() {
        return xlinkType;
    }

    public void setXlinkType(String xlinkType) {
        this.xlinkType = xlinkType;
    }

    public String getXlinkLabel() {
        return xlinkLabel;
    }

    public void setXlinkLabel(String xlinkLabel) {
        this.xlinkLabel = xlinkLabel;
    }

    public String getXlinkHRef() {
        return xlinkHRef;
    }

    public void setXlinkHRef(String xlinkHRef) {
        this.xlinkHRef = xlinkHRef;
    }

    @Override
    public DataQualityType getDataQualityType() {
        return dataQualityType;
    }

    @Override
    public void setDataQualityType(DataQualityType dataQualityType) {
        this.dataQualityType = dataQualityType;
    }

    @Override
    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public OffsetDateTime getValidTo() {
        return validTo;
    }

    @Override
    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    @Override
    public OffsetDateTime getDateValidFrom() {
        return dateValidFrom;
    }

    @Override
    public void setDateValidFrom(OffsetDateTime dateValidFrom) {
        this.dateValidFrom = dateValidFrom;
    }

    @Override
    public OffsetDateTime getDateValidTo() {
        return dateValidTo;
    }

    @Override
    public void setDateValidTo(OffsetDateTime dateValidTo) {
        this.dateValidTo = dateValidTo;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
