/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.Thoroughfare;

public class DeprecatedPropertiesOfPostOffice extends DeprecatedProperties {
    private Thoroughfare postalRoute;
    private PostalDeliveryPoint postBox;
    private PostCode postalCode;

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

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
