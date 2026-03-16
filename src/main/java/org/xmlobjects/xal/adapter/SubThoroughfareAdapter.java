/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.xal.model.SubThoroughfare;

import javax.xml.namespace.QName;

public class SubThoroughfareAdapter extends AbstractThoroughfareAdapter<SubThoroughfare> {

    @Override
    public SubThoroughfare createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubThoroughfare();
    }
}
