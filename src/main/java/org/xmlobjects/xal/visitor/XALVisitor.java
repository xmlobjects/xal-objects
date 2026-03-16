/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.visitor;

import org.xmlobjects.xal.model.*;

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
