/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.util.List;

public class GeoRSS extends AddressObject {
    private List<GenericElement> genericElements;

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public boolean isSetGenericElements() {
        return genericElements != null && !genericElements.isEmpty();
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
