/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public class SubThoroughfare extends AbstractThoroughfare {

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
