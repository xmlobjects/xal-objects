/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.Thoroughfare;

public class DeprecatedPropertiesOfCountry extends DeprecatedProperties {
    private AdministrativeArea administrativeArea;
    private Locality locality;
    private Thoroughfare thoroughfare;

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
}
