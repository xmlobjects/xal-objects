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

public interface XALVisitor {
    void visit(Address address);
    void visit(AdministrativeArea administrativeArea);
    void visit(Country country);
    void visit(FreeTextAddress freeTextAddress);
    void visit(GeoRSS geoRSS);
    void visit(Locality locality);
    void visit(LocationByCoordinates locationByCoordinates);
    void visit(PostalDeliveryPoint postalDeliveryPoint);
    void visit(PostalServiceElements postalServiceElements);
    void visit(PostCode postCode);
    void visit(PostOffice postOffice);
    void visit(Premises premises);
    void visit(RuralDelivery ruralDelivery);
    void visit(SubAdministrativeArea subAdministrativeArea);
    void visit(SubLocality subLocality);
    void visit(SubPremises subPremises);
    void visit(SubThoroughfare subThoroughfare);
    void visit(Thoroughfare thoroughfare);
}
