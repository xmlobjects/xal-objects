package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.AddressLines;

import javax.xml.namespace.QName;

public class AddressLinesAdapter implements ObjectBuilder<AddressLines>, ObjectSerializer<AddressLines> {

    @Override
    public AddressLines createObject(QName name) throws ObjectBuildException {
        return new AddressLines();
    }
}
