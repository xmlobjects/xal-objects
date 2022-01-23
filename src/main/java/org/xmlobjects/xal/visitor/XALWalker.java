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

package org.xmlobjects.xal.visitor;

import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.deprecated.*;

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

    public void visit(AddressObject addressObject) {
    }

    @Override
    public void visit(Address address) {
        visit((AddressObject) address);

        if (address.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAddress deprecatedProperties = address.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getPostalServiceElements() != null)
                deprecatedProperties.getPostalServiceElements().accept(this);
        }
    }

    @Override
    public void visit(AdministrativeArea administrativeArea) {
        visit((AddressObject) administrativeArea);

        if (shouldWalk && administrativeArea.getSubAdministrativeArea() != null)
            administrativeArea.getSubAdministrativeArea().accept(this);

        if (administrativeArea.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAdministrativeArea deprecatedProperties = administrativeArea.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getLocality() != null)
                deprecatedProperties.getLocality().accept(this);

            if (shouldWalk && deprecatedProperties.getPostOffice() != null)
                deprecatedProperties.getPostOffice().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }

    @Override
    public void visit(Country country) {
        visit((AddressObject) country);

        if (country.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfCountry deprecatedProperties = country.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getAdministrativeArea() != null)
                deprecatedProperties.getAdministrativeArea().accept(this);

            if (shouldWalk && deprecatedProperties.getLocality() != null)
                deprecatedProperties.getLocality().accept(this);

            if (shouldWalk && deprecatedProperties.getThoroughfare() != null)
                deprecatedProperties.getThoroughfare().accept(this);
        }
    }

    @Override
    public void visit(FreeTextAddress freeTextAddress) {
        visit((AddressObject) freeTextAddress);
    }

    @Override
    public void visit(GeoRSS geoRSS) {
        visit((AddressObject) geoRSS);
    }

    @Override
    public void visit(Locality locality) {
        visit((AddressObject) locality);

        if (shouldWalk && locality.getSubLocality() != null)
            locality.getSubLocality().accept(this);

        if (locality.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfLocality deprecatedProperties = locality.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getLargeMailUser() != null)
                deprecatedProperties.getLargeMailUser().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalRoute() != null)
                deprecatedProperties.getPostalRoute().accept(this);

            if (shouldWalk && deprecatedProperties.getPostBox() != null)
                deprecatedProperties.getPostBox().accept(this);

            if (shouldWalk && deprecatedProperties.getPostOffice() != null)
                deprecatedProperties.getPostOffice().accept(this);

            if (shouldWalk && deprecatedProperties.getThoroughfare() != null)
                deprecatedProperties.getThoroughfare().accept(this);

            if (shouldWalk && deprecatedProperties.getPremise() != null)
                deprecatedProperties.getPremise().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }

    @Override
    public void visit(LocationByCoordinates locationByCoordinates) {
        visit((AddressObject) locationByCoordinates);
    }

    @Override
    public void visit(PostalDeliveryPoint postalDeliveryPoint) {
        visit((AddressObject) postalDeliveryPoint);

        if (postalDeliveryPoint.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPostalDeliveryPoint deprecatedProperties = postalDeliveryPoint.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getFirm() != null)
                deprecatedProperties.getFirm().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }

    @Override
    public void visit(PostalServiceElements postalServiceElements) {
        visit((AddressObject) postalServiceElements);
    }

    @Override
    public void visit(PostCode postCode) {
        visit((AddressObject) postCode);

        if (postCode.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPostCode deprecatedProperties = postCode.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getPostTown() != null)
                deprecatedProperties.getPostTown().accept(this);
        }
    }

    @Override
    public void visit(PostOffice postOffice) {
        visit((AddressObject) postOffice);

        if (postOffice.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPostOffice deprecatedProperties = postOffice.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getPostalRoute() != null)
                deprecatedProperties.getPostalRoute().accept(this);

            if (shouldWalk && deprecatedProperties.getPostBox() != null)
                deprecatedProperties.getPostBox().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }

    @Override
    public void visit(Premises premises) {
        visit((AddressObject) premises);

        if (premises.isSetSubPremises()) {
            for (SubPremises subPremises : premises.getSubPremises()) {
                if (shouldWalk)
                    subPremises.accept(this);
            }
        }

        if (premises.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPremises deprecatedProperties = premises.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getPostBox() != null)
                deprecatedProperties.getPostBox().accept(this);

            if (shouldWalk && deprecatedProperties.getThoroughfare() != null)
                deprecatedProperties.getThoroughfare().accept(this);

            if (shouldWalk && deprecatedProperties.getMailStop() != null)
                deprecatedProperties.getMailStop().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);

            if (shouldWalk && deprecatedProperties.getFirm() != null)
                deprecatedProperties.getFirm().accept(this);

            if (shouldWalk && deprecatedProperties.getPremise() != null)
                deprecatedProperties.getPremise().accept(this);

            if (shouldWalk && deprecatedProperties.getDepartment() != null)
                deprecatedProperties.getDepartment().accept(this);
        }
    }

    @Override
    public void visit(RuralDelivery ruralDelivery) {
        visit((AddressObject) ruralDelivery);
    }

    @Override
    public void visit(SubAdministrativeArea subAdministrativeArea) {
        visit((AddressObject) subAdministrativeArea);

        if (subAdministrativeArea.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfSubAdministrativeArea deprecatedProperties = subAdministrativeArea.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getLocality() != null)
                deprecatedProperties.getLocality().accept(this);

            if (shouldWalk && deprecatedProperties.getPostOffice() != null)
                deprecatedProperties.getPostOffice().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }

    @Override
    public void visit(SubLocality subLocality) {
        visit((AddressObject) subLocality);

        if (subLocality.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfSubLocality deprecatedProperties = subLocality.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getPostBox() != null)
                deprecatedProperties.getPostBox().accept(this);

            if (shouldWalk && deprecatedProperties.getLargeMailUser() != null)
                deprecatedProperties.getLargeMailUser().accept(this);

            if (shouldWalk && deprecatedProperties.getPostOffice() != null)
                deprecatedProperties.getPostOffice().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalRoute() != null)
                deprecatedProperties.getPostalRoute().accept(this);

            if (shouldWalk && deprecatedProperties.getThoroughfare() != null)
                deprecatedProperties.getThoroughfare().accept(this);

            if (shouldWalk && deprecatedProperties.getPremise() != null)
                deprecatedProperties.getPremise().accept(this);

            if (shouldWalk && deprecatedProperties.getDependentLocality() != null)
                deprecatedProperties.getDependentLocality().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }

    @Override
    public void visit(SubPremises subPremises) {
        visit((AddressObject) subPremises);

        if (subPremises.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfSubPremises deprecatedProperties = subPremises.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getMailStop() != null)
                deprecatedProperties.getMailStop().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);

            if (shouldWalk && deprecatedProperties.getFirm() != null)
                deprecatedProperties.getFirm().accept(this);

            if (shouldWalk && deprecatedProperties.getSubPremise() != null)
                deprecatedProperties.getSubPremise().accept(this);
        }
    }

    @Override
    public void visit(SubThoroughfare subThoroughfare) {
        visit((AddressObject) subThoroughfare);
    }

    @Override
    public void visit(Thoroughfare thoroughfare) {
        visit((AddressObject) thoroughfare);

        if (thoroughfare.isSetSubThoroughfares()) {
            for (SubThoroughfare subThoroughfare : thoroughfare.getSubThoroughfares()) {
                if (shouldWalk)
                    subThoroughfare.accept(this);
            }
        }

        if (thoroughfare.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfThoroughfare deprecatedProperties = thoroughfare.getDeprecatedProperties();

            if (shouldWalk && deprecatedProperties.getDependentLocality() != null)
                deprecatedProperties.getDependentLocality().accept(this);

            if (shouldWalk && deprecatedProperties.getPostBox() != null)
                deprecatedProperties.getPostBox().accept(this);

            if (shouldWalk && deprecatedProperties.getPremise() != null)
                deprecatedProperties.getPremise().accept(this);

            if (shouldWalk && deprecatedProperties.getFirm() != null)
                deprecatedProperties.getFirm().accept(this);

            if (shouldWalk && deprecatedProperties.getPostalCode() != null)
                deprecatedProperties.getPostalCode().accept(this);
        }
    }
}
