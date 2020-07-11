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
import org.xmlobjects.xal.model.AddressObject;
import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.model.Country;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.GeoRSS;
import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.LocationByCoordinates;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostOffice;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.RuralDelivery;
import org.xmlobjects.xal.model.SubAdministrativeArea;
import org.xmlobjects.xal.model.SubLocality;
import org.xmlobjects.xal.model.SubPremises;
import org.xmlobjects.xal.model.SubThoroughfare;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.model.deprecated.PostalServiceElements;

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

    }

    @Override
    public void visit(AdministrativeArea administrativeArea) {
        visit((AddressObject) administrativeArea);

        if (shouldWalk && administrativeArea.getSubAdministrativeArea() != null)
            administrativeArea.getSubAdministrativeArea().accept(this);
    }

    @Override
    public void visit(Country country) {
        visit((AddressObject) country);
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
    }

    @Override
    public void visit(LocationByCoordinates locationByCoordinates) {
        visit((AddressObject) locationByCoordinates);
    }

    @Override
    public void visit(PostalDeliveryPoint postalDeliveryPoint) {
        visit((AddressObject) postalDeliveryPoint);
    }

    @Override
    public void visit(PostalServiceElements postalServiceElements) {
        visit((AddressObject) postalServiceElements);
    }

    @Override
    public void visit(PostCode postCode) {
        visit((AddressObject) postCode);
    }

    @Override
    public void visit(PostOffice postOffice) {
        visit((AddressObject) postOffice);
    }

    @Override
    public void visit(Premises premises) {
        visit((AddressObject) premises);

        for (SubPremises subPremises : premises.getSubPremises()) {
            if (shouldWalk)
                subPremises.accept(this);
        }
    }

    @Override
    public void visit(RuralDelivery ruralDelivery) {
        visit((AddressObject) ruralDelivery);
    }

    @Override
    public void visit(SubAdministrativeArea subAdministrativeArea) {
        visit((AddressObject) subAdministrativeArea);
    }

    @Override
    public void visit(SubLocality subLocality) {
        visit((AddressObject) subLocality);
    }

    @Override
    public void visit(SubPremises subPremises) {
        visit((AddressObject) subPremises);
    }

    @Override
    public void visit(SubThoroughfare subThoroughfare) {
        visit((AddressObject) subThoroughfare);
    }

    @Override
    public void visit(Thoroughfare thoroughfare) {
        visit((AddressObject) thoroughfare);

        for (SubThoroughfare subThoroughfare : thoroughfare.getSubThoroughfares()) {
            if (shouldWalk)
                subThoroughfare.accept(this);
        }
    }
}
