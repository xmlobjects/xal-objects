/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.Premises;

public class DeprecatedPropertiesOfPostalDeliveryPoint extends DeprecatedProperties {
    private Premises firm;
    private PostCode postalCode;

    public Premises getFirm() {
        return firm;
    }

    public void setFirm(Premises firm) {
        this.firm = asChild(firm);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
