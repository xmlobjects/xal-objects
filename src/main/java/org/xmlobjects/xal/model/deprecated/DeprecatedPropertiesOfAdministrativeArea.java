/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostOffice;

public class DeprecatedPropertiesOfAdministrativeArea extends DeprecatedProperties {
    private Locality locality;
    private PostOffice postOffice;
    private PostCode postalCode;

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = asChild(locality);
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = asChild(postOffice);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
