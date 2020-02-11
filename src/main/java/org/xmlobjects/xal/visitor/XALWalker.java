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

package org.xmlobjects.xal.visitor;

import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.AddressDetails;
import org.xmlobjects.xal.model.AddressIdentifier;
import org.xmlobjects.xal.model.AddressLatitude;
import org.xmlobjects.xal.model.AddressLatitudeDirection;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.AddressLines;
import org.xmlobjects.xal.model.AddressLongitude;
import org.xmlobjects.xal.model.AddressLongitudeDirection;
import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.model.AdministrativeAreaName;
import org.xmlobjects.xal.model.Barcode;
import org.xmlobjects.xal.model.BuildingName;
import org.xmlobjects.xal.model.Country;
import org.xmlobjects.xal.model.CountryName;
import org.xmlobjects.xal.model.CountryNameCode;
import org.xmlobjects.xal.model.Department;
import org.xmlobjects.xal.model.DepartmentName;
import org.xmlobjects.xal.model.DependentLocality;
import org.xmlobjects.xal.model.DependentLocalityName;
import org.xmlobjects.xal.model.DependentLocalityNumber;
import org.xmlobjects.xal.model.DependentThoroughfare;
import org.xmlobjects.xal.model.EndorsementLineCode;
import org.xmlobjects.xal.model.Firm;
import org.xmlobjects.xal.model.FirmName;
import org.xmlobjects.xal.model.KeyLineCode;
import org.xmlobjects.xal.model.LargeMailUser;
import org.xmlobjects.xal.model.LargeMailUserIdentifier;
import org.xmlobjects.xal.model.LargeMailUserName;
import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.LocalityName;
import org.xmlobjects.xal.model.MailStop;
import org.xmlobjects.xal.model.MailStopName;
import org.xmlobjects.xal.model.MailStopNumber;
import org.xmlobjects.xal.model.PostBox;
import org.xmlobjects.xal.model.PostBoxNumber;
import org.xmlobjects.xal.model.PostBoxNumberExtension;
import org.xmlobjects.xal.model.PostBoxNumberPrefix;
import org.xmlobjects.xal.model.PostBoxNumberSuffix;
import org.xmlobjects.xal.model.PostOffice;
import org.xmlobjects.xal.model.PostOfficeName;
import org.xmlobjects.xal.model.PostOfficeNumber;
import org.xmlobjects.xal.model.PostTown;
import org.xmlobjects.xal.model.PostTownName;
import org.xmlobjects.xal.model.PostTownSuffix;
import org.xmlobjects.xal.model.PostalCode;
import org.xmlobjects.xal.model.PostalCodeNumber;
import org.xmlobjects.xal.model.PostalCodeNumberExtension;
import org.xmlobjects.xal.model.PostalRoute;
import org.xmlobjects.xal.model.PostalRouteName;
import org.xmlobjects.xal.model.PostalRouteNumber;
import org.xmlobjects.xal.model.PostalServiceElements;
import org.xmlobjects.xal.model.Premise;
import org.xmlobjects.xal.model.PremiseLocation;
import org.xmlobjects.xal.model.PremiseName;
import org.xmlobjects.xal.model.PremiseNumber;
import org.xmlobjects.xal.model.PremiseNumberPrefix;
import org.xmlobjects.xal.model.PremiseNumberRange;
import org.xmlobjects.xal.model.PremiseNumberRangeFrom;
import org.xmlobjects.xal.model.PremiseNumberRangeTo;
import org.xmlobjects.xal.model.PremiseNumberSuffix;
import org.xmlobjects.xal.model.SortingCode;
import org.xmlobjects.xal.model.SubAdministrativeArea;
import org.xmlobjects.xal.model.SubAdministrativeAreaName;
import org.xmlobjects.xal.model.SubPremise;
import org.xmlobjects.xal.model.SubPremiseLocation;
import org.xmlobjects.xal.model.SubPremiseName;
import org.xmlobjects.xal.model.SubPremiseNumber;
import org.xmlobjects.xal.model.SubPremiseNumberPrefix;
import org.xmlobjects.xal.model.SubPremiseNumberSuffix;
import org.xmlobjects.xal.model.SupplementaryPostalServiceData;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.model.ThoroughfareLeadingType;
import org.xmlobjects.xal.model.ThoroughfareName;
import org.xmlobjects.xal.model.ThoroughfareNumber;
import org.xmlobjects.xal.model.ThoroughfareNumberContent;
import org.xmlobjects.xal.model.ThoroughfareNumberFrom;
import org.xmlobjects.xal.model.ThoroughfareNumberOrRange;
import org.xmlobjects.xal.model.ThoroughfareNumberPrefix;
import org.xmlobjects.xal.model.ThoroughfareNumberRange;
import org.xmlobjects.xal.model.ThoroughfareNumberSuffix;
import org.xmlobjects.xal.model.ThoroughfareNumberTo;
import org.xmlobjects.xal.model.ThoroughfarePostDirection;
import org.xmlobjects.xal.model.ThoroughfarePreDirection;
import org.xmlobjects.xal.model.ThoroughfareTrailingType;
import org.xmlobjects.xal.model.XAL;

public abstract class XALWalker implements XALVisitor {
    private boolean shouldWalk = true;

    public boolean shouldWalk() {
        return shouldWalk;
    }

    public void setShouldWalk(boolean shouldWalk) {
        this.shouldWalk = shouldWalk;
    }

    public void reset() {
        shouldWalk = true;
    }

    @Override
    public void visit(Address address) {
    }

    @Override
    public void visit(AddressDetails addressDetails) {
        if (shouldWalk && addressDetails.getPostalServiceElements() != null)
            addressDetails.getPostalServiceElements().accept(this);

        if (shouldWalk && addressDetails.getAddress() != null)
            addressDetails.getAddress().accept(this);

        if (shouldWalk && addressDetails.getAddressLines() != null)
            addressDetails.getAddressLines().accept(this);

        if (shouldWalk && addressDetails.getCountry() != null)
            addressDetails.getCountry().accept(this);

        if (shouldWalk && addressDetails.getAdministrativeArea() != null)
            addressDetails.getAdministrativeArea().accept(this);

        if (shouldWalk && addressDetails.getLocality() != null)
            addressDetails.getLocality().accept(this);

        if (shouldWalk && addressDetails.getThoroughfare() != null)
            addressDetails.getThoroughfare().accept(this);
    }

    @Override
    public void visit(AddressIdentifier addressIdentifier) {
    }

    @Override
    public void visit(AddressLatitude addressLatitude) {
    }

    @Override
    public void visit(AddressLatitudeDirection addressLatitudeDirection) {
    }

    @Override
    public void visit(AddressLine addressLine) {
    }

    @Override
    public void visit(AddressLines addressLines) {
        for (AddressLine addressLine : addressLines.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }
    }

    @Override
    public void visit(AddressLongitude addressLongitude) {
    }

    @Override
    public void visit(AddressLongitudeDirection addressLongitudeDirection) {
    }

    @Override
    public void visit(AdministrativeArea administrativeArea) {
        for (AddressLine addressLine : administrativeArea.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (AdministrativeAreaName administrativeAreaName : administrativeArea.getAdministrativeAreaNames()) {
            if (shouldWalk)
                administrativeAreaName.accept(this);
        }

        if (shouldWalk && administrativeArea.getSubAdministrativeArea() != null)
            administrativeArea.getSubAdministrativeArea().accept(this);

        if (shouldWalk && administrativeArea.getLocality() != null)
            administrativeArea.getLocality().accept(this);

        if (shouldWalk && administrativeArea.getPostOffice() != null)
            administrativeArea.getPostOffice().accept(this);

        if (shouldWalk && administrativeArea.getPostalCode() != null)
            administrativeArea.getPostalCode().accept(this);
    }

    @Override
    public void visit(AdministrativeAreaName administrativeAreaName) {
    }

    @Override
    public void visit(Barcode barcode) {
    }

    @Override
    public void visit(BuildingName buildingName) {
    }

    @Override
    public void visit(Country country) {
        for (AddressLine addressLine : country.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (CountryNameCode countryNameCode : country.getCountryNameCodes()) {
            if (shouldWalk)
                countryNameCode.accept(this);
        }

        for (CountryName countryName : country.getCountryNames()) {
            if (shouldWalk)
                countryName.accept(this);
        }

        if (shouldWalk && country.getAdministrativeArea() != null)
            country.getAdministrativeArea().accept(this);

        if (shouldWalk && country.getLocality() != null)
            country.getLocality().accept(this);

        if (shouldWalk && country.getThoroughfare() != null)
            country.getThoroughfare().accept(this);
    }

    @Override
    public void visit(CountryName countryName) {
    }

    @Override
    public void visit(CountryNameCode countryNameCode) {
    }

    @Override
    public void visit(Department department) {
        for (AddressLine addressLine : department.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (DepartmentName departmentName : department.getDepartmentNames()) {
            if (shouldWalk)
                departmentName.accept(this);
        }

        if (shouldWalk && department.getMailStop() != null)
            department.getMailStop().accept(this);

        if (shouldWalk && department.getPostalCode() != null)
            department.getPostalCode().accept(this);
    }

    @Override
    public void visit(DepartmentName departmentName) {
    }

    @Override
    public void visit(DependentLocality dependentLocality) {
        for (AddressLine addressLine : dependentLocality.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (DependentLocalityName dependentLocalityName : dependentLocality.getDependentLocalityNames()) {
            if (shouldWalk)
                dependentLocalityName.accept(this);
        }

        if (shouldWalk && dependentLocality.getDependentLocalityNumber() != null)
            dependentLocality.getDependentLocalityNumber().accept(this);

        if (shouldWalk && dependentLocality.getPostBox() != null)
            dependentLocality.getPostBox().accept(this);

        if (shouldWalk && dependentLocality.getLargeMailUser() != null)
            dependentLocality.getLargeMailUser().accept(this);

        if (shouldWalk && dependentLocality.getPostOffice() != null)
            dependentLocality.getPostOffice().accept(this);

        if (shouldWalk && dependentLocality.getPostalRoute() != null)
            dependentLocality.getPostalRoute().accept(this);

        if (shouldWalk && dependentLocality.getThoroughfare() != null)
            dependentLocality.getThoroughfare().accept(this);

        if (shouldWalk && dependentLocality.getPremise() != null)
            dependentLocality.getPremise().accept(this);

        if (shouldWalk && dependentLocality.getDependentLocality() != null)
            dependentLocality.getDependentLocality().accept(this);

        if (shouldWalk && dependentLocality.getPostalCode() != null)
            dependentLocality.getPostalCode().accept(this);
    }

    @Override
    public void visit(DependentLocalityName dependentLocalityName) {
    }

    @Override
    public void visit(DependentLocalityNumber dependentLocalityNumber) {
    }

    @Override
    public void visit(DependentThoroughfare dependentThoroughfare) {
        for (AddressLine addressLine : dependentThoroughfare.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        if (shouldWalk && dependentThoroughfare.getThoroughfarePreDirection() != null)
            dependentThoroughfare.getThoroughfarePreDirection().accept(this);

        if (shouldWalk && dependentThoroughfare.getThoroughfareLeadingType() != null)
            dependentThoroughfare.getThoroughfareLeadingType().accept(this);

        for (ThoroughfareName thoroughfareName : dependentThoroughfare.getThoroughfareNames()) {
            if (shouldWalk)
                thoroughfareName.accept(this);
        }

        if (shouldWalk && dependentThoroughfare.getThoroughfareTrailingType() != null)
            dependentThoroughfare.getThoroughfareTrailingType().accept(this);

        if (shouldWalk && dependentThoroughfare.getThoroughfarePostDirection() != null)
            dependentThoroughfare.getThoroughfarePostDirection().accept(this);
    }

    @Override
    public void visit(EndorsementLineCode endorsementLineCode) {
    }

    @Override
    public void visit(Firm firm) {
        for (AddressLine addressLine : firm.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (FirmName firmName : firm.getFirmNames()) {
            if (shouldWalk)
                firmName.accept(this);
        }

        for (Department department : firm.getDepartments()) {
            if (shouldWalk)
                department.accept(this);
        }

        if (shouldWalk && firm.getMailStop() != null)
            firm.getMailStop().accept(this);

        if (shouldWalk && firm.getPostalCode() != null)
            firm.getPostalCode().accept(this);
    }

    @Override
    public void visit(FirmName firmName) {
    }

    @Override
    public void visit(KeyLineCode keyLineCode) {
    }

    @Override
    public void visit(LargeMailUser largeMailUser) {
        for (AddressLine addressLine : largeMailUser.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (LargeMailUserName largeMailUserName : largeMailUser.getLargeMailUserNames()) {
            if (shouldWalk)
                largeMailUserName.accept(this);
        }

        if (shouldWalk && largeMailUser.getLargeMailUserIdentifier() != null)
            largeMailUser.getLargeMailUserIdentifier().accept(this);

        for (BuildingName buildingName : largeMailUser.getBuildingNames()) {
            if (shouldWalk)
                buildingName.accept(this);
        }

        if (shouldWalk && largeMailUser.getDepartment() != null)
            largeMailUser.getDepartment().accept(this);

        if (shouldWalk && largeMailUser.getPostBox() != null)
            largeMailUser.getPostBox().accept(this);

        if (shouldWalk && largeMailUser.getThoroughfare() != null)
            largeMailUser.getThoroughfare().accept(this);

        if (shouldWalk && largeMailUser.getPostalCode() != null)
            largeMailUser.getPostalCode().accept(this);
    }

    @Override
    public void visit(LargeMailUserIdentifier largeMailUserIdentifier) {
    }

    @Override
    public void visit(LargeMailUserName largeMailUserName) {
    }

    @Override
    public void visit(Locality locality) {
        for (AddressLine addressLine : locality.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (LocalityName localityName : locality.getLocalityNames()) {
            if (shouldWalk)
                localityName.accept(this);
        }

        if (shouldWalk && locality.getPostBox() != null)
            locality.getPostBox().accept(this);

        if (shouldWalk && locality.getLargeMailUser() != null)
            locality.getLargeMailUser().accept(this);

        if (shouldWalk && locality.getPostOffice() != null)
            locality.getPostOffice().accept(this);

        if (shouldWalk && locality.getPostalRoute() != null)
            locality.getPostalRoute().accept(this);

        if (shouldWalk && locality.getThoroughfare() != null)
            locality.getThoroughfare().accept(this);

        if (shouldWalk && locality.getPremise() != null)
            locality.getPremise().accept(this);

        if (shouldWalk && locality.getDependentLocality() != null)
            locality.getDependentLocality().accept(this);

        if (shouldWalk && locality.getPostalCode() != null)
            locality.getPostalCode().accept(this);
    }

    @Override
    public void visit(LocalityName localityName) {
    }

    @Override
    public void visit(MailStop mailStop) {
        for (AddressLine addressLine : mailStop.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        if (shouldWalk && mailStop.getMailStopName() != null)
            mailStop.getMailStopName().accept(this);

        if (shouldWalk && mailStop.getMailStopNumber() != null)
            mailStop.getMailStopNumber().accept(this);
    }

    @Override
    public void visit(MailStopName mailStopName) {
    }

    @Override
    public void visit(MailStopNumber mailStopNumber) {
    }

    @Override
    public void visit(PostalCode postalCode) {
        for (AddressLine addressLine : postalCode.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PostalCodeNumber postalCodeNumber : postalCode.getPostalCodeNumbers()) {
            if (shouldWalk)
                postalCodeNumber.accept(this);
        }

        for (PostalCodeNumberExtension postalCodeNumberExtension : postalCode.getPostalCodeNumberExtensions()) {
            if (shouldWalk)
                postalCodeNumberExtension.accept(this);
        }

        if (shouldWalk && postalCode.getPostTown() != null)
            postalCode.getPostTown().accept(this);
    }

    @Override
    public void visit(PostalCodeNumber postalCodeNumber) {
    }

    @Override
    public void visit(PostalCodeNumberExtension postalCodeNumberExtension) {
    }

    @Override
    public void visit(PostalRoute postalRoute) {
        for (AddressLine addressLine : postalRoute.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PostalRouteName postalRouteName : postalRoute.getPostalRouteNames()) {
            if (shouldWalk)
                postalRouteName.accept(this);
        }

        if (shouldWalk && postalRoute.getPostalRouteNumber() != null)
            postalRoute.getPostalRouteNumber().accept(this);

        if (shouldWalk && postalRoute.getPostBox() != null)
            postalRoute.getPostBox().accept(this);
    }

    @Override
    public void visit(PostalRouteName postalRouteName) {
    }

    @Override
    public void visit(PostalRouteNumber postalRouteNumber) {
    }

    @Override
    public void visit(PostalServiceElements postalServiceElements) {
        for (AddressIdentifier addressIdentifier : postalServiceElements.getAddressIdentifier()) {
            if (shouldWalk)
                addressIdentifier.accept(this);
        }

        if (shouldWalk && postalServiceElements.getEndorsementLineCode() != null)
            postalServiceElements.getEndorsementLineCode().accept(this);

        if (shouldWalk && postalServiceElements.getKeyLineCode() != null)
            postalServiceElements.getKeyLineCode().accept(this);

        if (shouldWalk && postalServiceElements.getBarcode() != null)
            postalServiceElements.getBarcode().accept(this);

        if (shouldWalk && postalServiceElements.getSortingCode() != null)
            postalServiceElements.getSortingCode().accept(this);

        if (shouldWalk && postalServiceElements.getAddressLatitude() != null)
            postalServiceElements.getAddressLatitude().accept(this);

        if (shouldWalk && postalServiceElements.getAddressLatitudeDirection() != null)
            postalServiceElements.getAddressLatitudeDirection().accept(this);

        if (shouldWalk && postalServiceElements.getAddressLongitude() != null)
            postalServiceElements.getAddressLongitude().accept(this);

        if (shouldWalk && postalServiceElements.getAddressLongitudeDirection() != null)
            postalServiceElements.getAddressLongitudeDirection().accept(this);

        for (SupplementaryPostalServiceData supplementaryPostalServiceData : postalServiceElements.getSupplementaryPostalServiceData()) {
            if (shouldWalk)
                supplementaryPostalServiceData.accept(this);
        }
    }

    @Override
    public void visit(PostBox postBox) {
        for (AddressLine addressLine : postBox.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        if (shouldWalk && postBox.getPostBoxNumber() != null)
            postBox.getPostBoxNumber().accept(this);

        if (shouldWalk && postBox.getPostBoxNumberPrefix() != null)
            postBox.getPostBoxNumberPrefix().accept(this);

        if (shouldWalk && postBox.getPostBoxNumberSuffix() != null)
            postBox.getPostBoxNumberSuffix().accept(this);

        if (shouldWalk && postBox.getPostBoxNumberExtension() != null)
            postBox.getPostBoxNumberExtension().accept(this);

        if (shouldWalk && postBox.getFirm() != null)
            postBox.getFirm().accept(this);

        if (shouldWalk && postBox.getPostalCode() != null)
            postBox.getPostalCode().accept(this);
    }

    @Override
    public void visit(PostBoxNumber postBoxNumber) {
    }

    @Override
    public void visit(PostBoxNumberExtension postBoxNumberExtension) {
    }

    @Override
    public void visit(PostBoxNumberPrefix postBoxNumberPrefix) {
    }

    @Override
    public void visit(PostBoxNumberSuffix postBoxNumberSuffix) {
    }

    @Override
    public void visit(PostOffice postOffice) {
        for (AddressLine addressLine : postOffice.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PostOfficeName postOfficeName : postOffice.getPostOfficeNames()) {
            if (shouldWalk)
                postOfficeName.accept(this);
        }

        if (shouldWalk && postOffice.getPostOfficeNumber() != null)
            postOffice.getPostOfficeNumber().accept(this);

        if (shouldWalk && postOffice.getPostalRoute() != null)
            postOffice.getPostalRoute().accept(this);

        if (shouldWalk && postOffice.getPostBox() != null)
            postOffice.getPostBox().accept(this);

        if (shouldWalk && postOffice.getPostalCode() != null)
            postOffice.getPostalCode().accept(this);
    }

    @Override
    public void visit(PostOfficeName postOfficeName) {
    }

    @Override
    public void visit(PostOfficeNumber postOfficeNumber) {
    }

    @Override
    public void visit(PostTown postTown) {
        for (AddressLine addressLine : postTown.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PostTownName postTownName : postTown.getPostTownNames()) {
            if (shouldWalk)
                postTownName.accept(this);
        }

        if (shouldWalk && postTown.getPostTownSuffix() != null)
            postTown.getPostTownSuffix().accept(this);
    }

    @Override
    public void visit(PostTownName postTownName) {
    }

    @Override
    public void visit(PostTownSuffix postTownSuffix) {
    }

    @Override
    public void visit(Premise premise) {
        for (AddressLine addressLine : premise.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PremiseName premiseName : premise.getPremiseNames()) {
            if (shouldWalk)
                premiseName.accept(this);
        }

        if (shouldWalk && premise.getPremiseLocation() != null)
            premise.getPremiseLocation().accept(this);

        for (PremiseNumber premiseNumber : premise.getPremiseNumbers()) {
            if (shouldWalk)
                premiseNumber.accept(this);
        }

        if (shouldWalk && premise.getPremiseNumberRange() != null)
            premise.getPremiseNumberRange().accept(this);

        for (PremiseNumberPrefix premiseNumberPrefix : premise.getPremiseNumberPrefixes()) {
            if (shouldWalk)
                premiseNumberPrefix.accept(this);
        }

        for (PremiseNumberSuffix premiseNumberSuffix : premise.getPremiseNumberSuffixes()) {
            if (shouldWalk)
                premiseNumberSuffix.accept(this);
        }

        for (BuildingName buildingName : premise.getBuildingNames()) {
            if (shouldWalk)
                buildingName.accept(this);
        }

        for (SubPremise subPremise : premise.getSubPremises()) {
            if (shouldWalk)
                subPremise.accept(this);
        }

        if (shouldWalk && premise.getFirm() != null)
            premise.getFirm().accept(this);

        if (shouldWalk && premise.getMailStop() != null)
            premise.getMailStop().accept(this);

        if (shouldWalk && premise.getPostalCode() != null)
            premise.getPostalCode().accept(this);

        if (shouldWalk && premise.getPremise() != null)
            premise.getPremise().accept(this);
    }

    @Override
    public void visit(PremiseLocation premiseLocation) {
    }

    @Override
    public void visit(PremiseName premiseName) {
    }

    @Override
    public void visit(PremiseNumber premiseNumber) {
    }

    @Override
    public void visit(PremiseNumberPrefix premiseNumberPrefix) {
    }

    @Override
    public void visit(PremiseNumberRange premiseNumberRange) {
        if (shouldWalk && premiseNumberRange.getPremiseNumberRangeFrom() != null)
            premiseNumberRange.getPremiseNumberRangeFrom().accept(this);

        if (shouldWalk && premiseNumberRange.getPremiseNumberRangeFrom() != null)
            premiseNumberRange.getPremiseNumberRangeFrom().accept(this);
    }

    @Override
    public void visit(PremiseNumberRangeFrom premiseNumberRangeFrom) {
        for (AddressLine addressLine : premiseNumberRangeFrom.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PremiseNumberPrefix premiseNumberPrefix : premiseNumberRangeFrom.getPremiseNumberPrefixes()) {
            if (shouldWalk)
                premiseNumberPrefix.accept(this);
        }

        for (PremiseNumber premiseNumber : premiseNumberRangeFrom.getPremiseNumbers()) {
            if (shouldWalk)
                premiseNumber.accept(this);
        }

        for (PremiseNumberSuffix premiseNumberSuffix : premiseNumberRangeFrom.getPremiseNumberSuffixes()) {
            if (shouldWalk)
                premiseNumberSuffix.accept(this);
        }
    }

    @Override
    public void visit(PremiseNumberRangeTo premiseNumberRangeTo) {
        for (AddressLine addressLine : premiseNumberRangeTo.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (PremiseNumberPrefix premiseNumberPrefix : premiseNumberRangeTo.getPremiseNumberPrefixes()) {
            if (shouldWalk)
                premiseNumberPrefix.accept(this);
        }

        for (PremiseNumber premiseNumber : premiseNumberRangeTo.getPremiseNumbers()) {
            if (shouldWalk)
                premiseNumber.accept(this);
        }

        for (PremiseNumberSuffix premiseNumberSuffix : premiseNumberRangeTo.getPremiseNumberSuffixes()) {
            if (shouldWalk)
                premiseNumberSuffix.accept(this);
        }
    }

    @Override
    public void visit(PremiseNumberSuffix premiseNumberSuffix) {
    }

    @Override
    public void visit(SortingCode sortingCode) {
    }

    @Override
    public void visit(SubAdministrativeArea subAdministrativeArea) {
        for (AddressLine addressLine : subAdministrativeArea.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (SubAdministrativeAreaName subAdministrativeAreaName : subAdministrativeArea.getSubAdministrativeAreaNames()) {
            if (shouldWalk)
                subAdministrativeAreaName.accept(this);
        }

        if (shouldWalk && subAdministrativeArea.getLocality() != null)
            subAdministrativeArea.getLocality().accept(this);

        if (shouldWalk && subAdministrativeArea.getPostOffice() != null)
            subAdministrativeArea.getPostOffice().accept(this);

        if (shouldWalk && subAdministrativeArea.getPostalCode() != null)
            subAdministrativeArea.getPostalCode().accept(this);
    }

    @Override
    public void visit(SubAdministrativeAreaName subAdministrativeAreaName) {
    }

    @Override
    public void visit(SubPremise subPremise) {
        for (AddressLine addressLine : subPremise.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (SubPremiseName subPremiseName : subPremise.getSubPremiseNames()) {
            if (shouldWalk)
                subPremiseName.accept(this);
        }

        if (shouldWalk && subPremise.getSubPremiseLocation() != null)
            subPremise.getSubPremiseLocation().accept(this);

        for (SubPremiseNumber subPremiseNumber : subPremise.getSubPremiseNumbers()) {
            if (shouldWalk)
                subPremiseNumber.accept(this);
        }

        for (SubPremiseNumberPrefix subPremiseNumberPrefix : subPremise.getSubPremiseNumberPrefixes()) {
            if (shouldWalk)
                subPremiseNumberPrefix.accept(this);
        }

        for (SubPremiseNumberSuffix subPremiseNumberSuffix : subPremise.getSubPremiseNumberSuffixes()) {
            if (shouldWalk)
                subPremiseNumberSuffix.accept(this);
        }

        for (BuildingName buildingName : subPremise.getBuildingNames()) {
            if (shouldWalk)
                buildingName.accept(this);
        }

        if (shouldWalk && subPremise.getFirm() != null)
            subPremise.getFirm().accept(this);

        if (shouldWalk && subPremise.getMailStop() != null)
            subPremise.getMailStop().accept(this);

        if (shouldWalk && subPremise.getPostalCode() != null)
            subPremise.getPostalCode().accept(this);

        if (shouldWalk && subPremise.getSubPremise() != null)
            subPremise.getSubPremise().accept(this);
    }

    @Override
    public void visit(SubPremiseLocation subPremiseLocation) {
    }

    @Override
    public void visit(SubPremiseName subPremiseName) {
    }

    @Override
    public void visit(SubPremiseNumber subPremiseNumber) {

    }

    @Override
    public void visit(SubPremiseNumberPrefix subPremiseNumberPrefix) {
    }

    @Override
    public void visit(SubPremiseNumberSuffix subPremiseNumberSuffix) {
    }

    @Override
    public void visit(SupplementaryPostalServiceData supplementaryPostalServiceData) {
    }

    @Override
    public void visit(Thoroughfare thoroughfare) {
        for (AddressLine addressLine : thoroughfare.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        for (ThoroughfareNumberOrRange thoroughfareNumberOrRange : thoroughfare.getThoroughfareNumberOrRanges()) {
            if (shouldWalk) {
                if (thoroughfareNumberOrRange.isSetThoroughfareNumber())
                    thoroughfareNumberOrRange.getThoroughfareNumber().accept(this);
                else if (thoroughfareNumberOrRange.isSetThoroughfareNumberRange())
                    thoroughfareNumberOrRange.getThoroughfareNumberRange().accept(this);
            }
        }

        for (ThoroughfareNumberPrefix thoroughfareNumberPrefix : thoroughfare.getThoroughfareNumberPrefixes()) {
            if (shouldWalk)
                thoroughfareNumberPrefix.accept(this);
        }

        for (ThoroughfareNumberSuffix thoroughfareNumberSuffix : thoroughfare.getThoroughfareNumberSuffixes()) {
            if (shouldWalk)
                thoroughfareNumberSuffix.accept(this);
        }

        if (shouldWalk && thoroughfare.getThoroughfarePreDirection() != null)
            thoroughfare.getThoroughfarePreDirection().accept(this);

        if (shouldWalk && thoroughfare.getThoroughfareLeadingType() != null)
            thoroughfare.getThoroughfareLeadingType().accept(this);

        for (ThoroughfareName thoroughfareName : thoroughfare.getThoroughfareNames()) {
            if (shouldWalk)
                thoroughfareName.accept(this);
        }

        if (shouldWalk && thoroughfare.getThoroughfareTrailingType() != null)
            thoroughfare.getThoroughfareTrailingType().accept(this);

        if (shouldWalk && thoroughfare.getThoroughfarePostDirection() != null)
            thoroughfare.getThoroughfarePostDirection().accept(this);

        if (shouldWalk && thoroughfare.getDependentThoroughfare() != null)
            thoroughfare.getDependentThoroughfare().accept(this);

        if (shouldWalk && thoroughfare.getDependentLocality() != null)
            thoroughfare.getDependentLocality().accept(this);

        if (shouldWalk && thoroughfare.getPremise() != null)
            thoroughfare.getPremise().accept(this);

        if (shouldWalk && thoroughfare.getFirm() != null)
            thoroughfare.getFirm().accept(this);

        if (shouldWalk && thoroughfare.getPostalCode() != null)
            thoroughfare.getPostalCode().accept(this);
    }

    @Override
    public void visit(ThoroughfareLeadingType thoroughfareLeadingType) {
    }

    @Override
    public void visit(ThoroughfareName thoroughfareName) {
    }

    @Override
    public void visit(ThoroughfareNumber thoroughfareNumber) {
    }

    @Override
    public void visit(ThoroughfareNumberFrom thoroughfareNumberFrom) {
        for (ThoroughfareNumberContent thoroughfareNumberContent : thoroughfareNumberFrom.getContents()) {
            if (shouldWalk) {
                if (thoroughfareNumberContent.isSetAddressLine())
                    thoroughfareNumberContent.getAddressLine().accept(this);
                else if (thoroughfareNumberContent.isSetThoroughfareNumber())
                    thoroughfareNumberContent.getThoroughfareNumber().accept(this);
                else if (thoroughfareNumberContent.isSetThoroughfareNumberPrefix())
                    thoroughfareNumberContent.getThoroughfareNumberPrefix().accept(this);
                else if (thoroughfareNumberContent.isSetThoroughfareNumberSuffix())
                    thoroughfareNumberContent.getThoroughfareNumberSuffix().accept(this);
            }
        }
    }

    @Override
    public void visit(ThoroughfareNumberPrefix thoroughfareNumberPrefix) {
    }

    @Override
    public void visit(ThoroughfareNumberRange thoroughfareNumberRange) {
        for (AddressLine addressLine : thoroughfareNumberRange.getAddressLines()) {
            if (shouldWalk)
                addressLine.accept(this);
        }

        if (shouldWalk && thoroughfareNumberRange.getThoroughfareNumberFrom() != null)
            thoroughfareNumberRange.getThoroughfareNumberFrom().accept(this);

        if (shouldWalk && thoroughfareNumberRange.getThoroughfareNumberTo() != null)
            thoroughfareNumberRange.getThoroughfareNumberTo().accept(this);
    }

    @Override
    public void visit(ThoroughfareNumberSuffix thoroughfareNumberSuffix) {
    }

    @Override
    public void visit(ThoroughfareNumberTo thoroughfareNumberTo) {
        for (ThoroughfareNumberContent thoroughfareNumberContent : thoroughfareNumberTo.getContents()) {
            if (shouldWalk) {
                if (thoroughfareNumberContent.isSetAddressLine())
                    thoroughfareNumberContent.getAddressLine().accept(this);
                else if (thoroughfareNumberContent.isSetThoroughfareNumber())
                    thoroughfareNumberContent.getThoroughfareNumber().accept(this);
                else if (thoroughfareNumberContent.isSetThoroughfareNumberPrefix())
                    thoroughfareNumberContent.getThoroughfareNumberPrefix().accept(this);
                else if (thoroughfareNumberContent.isSetThoroughfareNumberSuffix())
                    thoroughfareNumberContent.getThoroughfareNumberSuffix().accept(this);
            }
        }
    }

    @Override
    public void visit(ThoroughfarePostDirection thoroughfarePostDirection) {
    }

    @Override
    public void visit(ThoroughfarePreDirection thoroughfarePreDirection) {
    }

    @Override
    public void visit(ThoroughfareTrailingType thoroughfareTrailingType) {
    }

    @Override
    public void visit(XAL xal) {
        for (AddressDetails addressDetails : xal.getAddressDetails()) {
            if (shouldWalk)
                addressDetails.accept(this);
        }
    }
}
