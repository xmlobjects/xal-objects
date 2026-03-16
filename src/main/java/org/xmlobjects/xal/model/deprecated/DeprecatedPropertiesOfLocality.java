/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.types.Identifier;

public class DeprecatedPropertiesOfLocality extends DeprecatedProperties {
    private Identifier postTownSuffix;
    private Premises largeMailUser;
    private Thoroughfare postalRoute;
    private PostalDeliveryPoint postBox;
    private PostOffice postOffice;
    private Thoroughfare thoroughfare;
    private Premises premise;
    private PostCode postalCode;

    public Identifier getPostTownSuffix() {
        return postTownSuffix;
    }

    public void setPostTownSuffix(Identifier postTownSuffix) {
        this.postTownSuffix = asChild(postTownSuffix);
    }

    public Premises getLargeMailUser() {
        return largeMailUser;
    }

    public void setLargeMailUser(Premises largeMailUser) {
        this.largeMailUser = asChild(largeMailUser);
    }

    public Thoroughfare getPostalRoute() {
        return postalRoute;
    }

    public void setPostalRoute(Thoroughfare postalRoute) {
        this.postalRoute = asChild(postalRoute);
    }

    public PostalDeliveryPoint getPostBox() {
        return postBox;
    }

    public void setPostBox(PostalDeliveryPoint postBox) {
        this.postBox = asChild(postBox);
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = asChild(postOffice);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        this.thoroughfare = asChild(thoroughfare);
    }

    public Premises getPremise() {
        return premise;
    }

    public void setPremise(Premises premise) {
        this.premise = asChild(premise);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
