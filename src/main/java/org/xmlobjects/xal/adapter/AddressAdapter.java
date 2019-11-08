package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.PostalServiceElements;

import javax.xml.namespace.QName;

public class AddressAdapter implements ObjectBuilder<Address>, ObjectSerializer<Address> {

    @Override
    public Address createObject(QName name) throws ObjectBuildException {
        return new Address();
    }
}
