package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.Country;

import javax.xml.namespace.QName;

public class CountryAdapter implements ObjectBuilder<Country>, ObjectSerializer<Country> {

    @Override
    public Country createObject(QName name) throws ObjectBuildException {
        return new Country();
    }
}
