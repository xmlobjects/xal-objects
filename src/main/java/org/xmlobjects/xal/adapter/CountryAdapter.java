/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.Country;
import org.xmlobjects.xal.model.CountryName;
import org.xmlobjects.xal.model.CountryNameCode;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class CountryAdapter implements ObjectBuilder<Country>, ObjectSerializer<Country> {

    @Override
    public Country createObject(QName name) throws ObjectBuildException {
        return new Country();
    }

    @Override
    public void initializeObject(Country object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(Country object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "CountryNameCode":
                    object.getCountryNameCodes().add(reader.getObjectUsingBuilder(CountryNameCodeAdapter.class));
                    break;
                case "CountryName":
                    object.getCountryNames().add(reader.getObjectUsingBuilder(CountryNameAdapter.class));
                    break;
                case "AdministrativeArea":
                    object.setAdministrativeArea(reader.getObjectUsingBuilder(AdministrativeAreaAdapter.class));
                    break;
                case "Locality":
                    object.setLocality(reader.getObjectUsingBuilder(LocalityAdapter.class));
                    break;
                case "Thoroughfare":
                    object.setThoroughfare(reader.getObjectUsingBuilder(ThoroughfareAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(Country object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "Country");
    }

    @Override
    public void initializeElement(Element element, Country object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(Country object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (CountryNameCode countryNameCode : object.getCountryNameCodes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "CountryNameCode"), countryNameCode, CountryNameCodeAdapter.class, namespaces);

        for (CountryName countryName : object.getCountryNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "CountryName"), countryName, CountryNameAdapter.class, namespaces);

        if (object.isSetAdministrativeArea())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeArea"), object.getAdministrativeArea(), AdministrativeAreaAdapter.class, namespaces);
        else if (object.isSetLocality())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), object.getLocality(), LocalityAdapter.class, namespaces);
        else if (object.isSetThoroughfare())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), object.getThoroughfare(), ThoroughfareAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
