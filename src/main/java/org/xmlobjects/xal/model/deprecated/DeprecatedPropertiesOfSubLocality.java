/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.*;

public class DeprecatedPropertiesOfSubLocality extends DeprecatedProperties {
    private PostalDeliveryPoint postBox;
    private Premises largeMailUser;
    private PostOffice postOffice;
    private Thoroughfare postalRoute;
    private Thoroughfare thoroughfare;
    private Premises premise;
    private SubLocality dependentLocality;
    private PostCode postalCode;

    public PostalDeliveryPoint getPostBox() {
        return postBox;
    }

    public void setPostBox(PostalDeliveryPoint postBox) {
        this.postBox = asChild(postBox);
    }

    public Premises getLargeMailUser() {
        return largeMailUser;
    }

    public void setLargeMailUser(Premises largeMailUser) {
        this.largeMailUser = asChild(largeMailUser);
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = asChild(postOffice);
    }

    public Thoroughfare getPostalRoute() {
        return postalRoute;
    }

    public void setPostalRoute(Thoroughfare postalRoute) {
        this.postalRoute = asChild(postalRoute);
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

    public SubLocality getDependentLocality() {
        return dependentLocality;
    }

    public void setDependentLocality(SubLocality dependentLocality) {
        this.dependentLocality = asChild(dependentLocality);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
