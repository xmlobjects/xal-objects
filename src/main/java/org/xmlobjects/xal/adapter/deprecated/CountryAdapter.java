/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2024 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xmlobjects.xal.adapter.deprecated;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.model.Child;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.CountryNameAdapter;
import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfCountry;
import org.xmlobjects.xal.model.types.CountryName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class CountryAdapter extends AddressObjectAdapter<Country> {

    @Override
    public Country createObject(QName name, Object parent) throws ObjectBuildException {
        Country object = new Country();
        if (parent instanceof Child child)
            object.setParent(child);

        return object;
    }

    @Override
    public void buildChildObject(Country object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            Address address = object.getParent(Address.class);
            switch (name.getLocalPart()) {
                case "AddressLine":
                    if (address != null) {
                        if (address.getFreeTextAddress() == null)
                            address.setFreeTextAddress(new FreeTextAddress());

                        address.getFreeTextAddress().getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    }
                    break;
                case "CountryNameCode":
                case "CountryName":
                    object.getNameElements().add(reader.getObjectUsingBuilder(CountryNameAdapter.class));
                    break;
                case "AdministrativeArea":
                    AdministrativeArea administrativeArea = reader.getObjectUsingBuilder(AdministrativeAreaAdapter.class);
                    if (address != null && address.getAdministrativeArea() == null)
                        address.setAdministrativeArea(administrativeArea);
                    else
                        object.getDeprecatedProperties().setAdministrativeArea(administrativeArea);
                    break;
                case "Locality":
                    Locality locality = reader.getObjectUsingBuilder(LocalityAdapter.class);
                    if (address != null && address.getLocality() == null)
                        address.setLocality(locality);
                    else
                        object.getDeprecatedProperties().setLocality(locality);
                    break;
                case "Thoroughfare":
                    Thoroughfare thoroughfare = reader.getObjectUsingBuilder(ThoroughfareAdapter.class);
                    if (address != null && address.getThoroughfare() == null)
                        address.setThoroughfare(thoroughfare);
                    else
                        object.getDeprecatedProperties().setThoroughfare(thoroughfare);
                    break;
            }
        }
    }

    @Override
    public void writeChildElements(Country object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Address address = object.getParent(Address.class);
        DeprecatedPropertiesOfCountry properties = object.hasDeprecatedProperties() ?
                object.getDeprecatedProperties() :
                null;

        if (object.isSetNameElements()) {
            for (CountryName nameElement : object.getNameElements())
                writer.writeObjectUsingSerializer(nameElement, CountryNameAdapter.class, namespaces);
        }

        AdministrativeArea administrativeArea = null;
        if (address != null && address.getAdministrativeArea() != null) {
            administrativeArea = address.getAdministrativeArea();
        } else if (properties != null) {
            administrativeArea = properties.getAdministrativeArea();
        }

        Locality locality = null;
        if (address != null && address.getLocality() != null) {
            locality = address.getLocality();
        } else if (properties != null) {
            locality = properties.getLocality();
        }

        Thoroughfare thoroughfare = null;
        if (address != null && address.getThoroughfare() != null) {
            thoroughfare = address.getThoroughfare();
        } else if (properties != null) {
            thoroughfare = properties.getThoroughfare();
        }

        if (administrativeArea != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeArea"), administrativeArea, AdministrativeAreaAdapter.class, namespaces);
        else if (locality != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), locality, LocalityAdapter.class, namespaces);
        else if (thoroughfare != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), thoroughfare, ThoroughfareAdapter.class, namespaces);
    }
}
