/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.visitor.XALVisitor;

public abstract class AddressObject extends XALObject {
    private DeprecatedProperties deprecatedProperties;
    private GenericAttributes otherAttributes;

    public abstract void accept(XALVisitor visitor);

    public DeprecatedProperties getDeprecatedProperties() {
        if (deprecatedProperties == null)
            deprecatedProperties = asChild(createDeprecatedProperties());

        return deprecatedProperties;
    }

    public boolean hasDeprecatedProperties() {
        return deprecatedProperties != null;
    }

    protected DeprecatedProperties createDeprecatedProperties() {
        return null;
    }

    public GenericAttributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new GenericAttributes();

        return otherAttributes;
    }

    public boolean isSetOtherAttributes() {
        return otherAttributes != null && !otherAttributes.isEmpty();
    }

    public void setOtherAttributes(GenericAttributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
