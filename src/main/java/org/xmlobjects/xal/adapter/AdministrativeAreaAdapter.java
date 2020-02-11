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

import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.model.AdministrativeAreaName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "AdministrativeArea", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class AdministrativeAreaAdapter implements ObjectBuilder<AdministrativeArea>, ObjectSerializer<AdministrativeArea> {

    @Override
    public AdministrativeArea createObject(QName name) throws ObjectBuildException {
        return new AdministrativeArea();
    }

    @Override
    public void initializeObject(AdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("UsageType").ifPresent(object::setUsageType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(AdministrativeArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "AdministrativeAreaName":
                    object.getAdministrativeAreaNames().add(reader.getObjectUsingBuilder(AdministrativeAreaNameAdapter.class));
                    break;
                case "SubAdministrativeArea":
                    object.setSubAdministrativeArea(reader.getObjectUsingBuilder(SubAdministrativeAreaAdapter.class));
                    break;
                case "Locality":
                    object.setLocality(reader.getObjectUsingBuilder(LocalityAdapter.class));
                    break;
                case "PostOffice":
                    object.setPostOffice(reader.getObjectUsingBuilder(PostOfficeAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(AdministrativeArea object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeArea");
    }

    @Override
    public void initializeElement(Element element, AdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        element.addAttribute("UsageType", object.getUsageType());
        element.addAttribute("Indicator", object.getIndicator());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(AdministrativeArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (AdministrativeAreaName administrativeAreaName : object.getAdministrativeAreaNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeAreaName"), administrativeAreaName, AdministrativeAreaNameAdapter.class, namespaces);

        if (object.getSubAdministrativeArea() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubAdministrativeArea"), object.getSubAdministrativeArea(), SubAdministrativeAreaAdapter.class, namespaces);

        if (object.isSetLocality())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), object.getLocality(), LocalityAdapter.class, namespaces);
        else if (object.isSetPostOffice())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostOffice"), object.getPostOffice(), PostOfficeAdapter.class, namespaces);
        else if (object.isSetPostalCode())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
