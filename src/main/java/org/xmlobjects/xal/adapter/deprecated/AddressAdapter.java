/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class AddressAdapter implements ObjectBuilder<FreeTextAddress> {

    @Override
    public FreeTextAddress createObject(QName name, Object parent) throws ObjectBuildException {
        return new FreeTextAddress();
    }

    @Override
    public void initializeObject(FreeTextAddress object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
    }
}
