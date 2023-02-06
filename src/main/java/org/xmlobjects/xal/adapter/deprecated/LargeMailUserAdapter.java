/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2023 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.types.BuildingNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.LargeMailUserIdentifierAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.LargeMailUserNameAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfPremises;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;
import org.xmlobjects.xal.model.types.PremisesType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class LargeMailUserAdapter extends AddressObjectAdapter<Premises> {

    @Override
    public Premises createObject(QName name, Object parent) throws ObjectBuildException {
        Premises object = new Premises(PremisesType.LARGE_MAIL_USER);
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Premises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> object.getOtherAttributes().add("Type", v));
    }

    @Override
    public void buildChildObject(Premises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "LargeMailUserName":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(LargeMailUserNameAdapter.class)));
                    break;
                case "LargeMailUserIdentifier":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(LargeMailUserIdentifierAdapter.class)));
                    break;
                case "BuildingName":
                    object.getDeprecatedProperties().getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
                    break;
                case "Department":
                    object.getDeprecatedProperties().setDepartment(reader.getObjectUsingBuilder(DepartmentAdapter.class));
                    break;
                case "PostBox":
                    object.getDeprecatedProperties().setPostBox(reader.getObjectUsingBuilder(PostBoxAdapter.class));
                    break;
                case "Thoroughfare":
                    object.getDeprecatedProperties().setThoroughfare(reader.getObjectUsingBuilder(ThoroughfareAdapter.class));
                    break;
                case "PostalCode":
                    object.getDeprecatedProperties().setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, Premises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(Premises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Identifier identifier = null;

        if (object.isSetNameElementOrNumber()) {
            for (PremisesNameOrNumber nameElementOrNumber : object.getNameElementOrNumber()) {
                if (nameElementOrNumber.isSetNameElement())
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUserName"), nameElementOrNumber.getNameElement(), LargeMailUserNameAdapter.class, namespaces);
                else if (identifier == null)
                    identifier = nameElementOrNumber.getNumber();
            }
        }

        if (identifier != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUserIdentifier"), identifier, LargeMailUserIdentifierAdapter.class, namespaces);

        if (object.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPremises properties = object.getDeprecatedProperties();

            if (properties.isSetBuildingNames()) {
                for (Identifier buildingName : properties.getBuildingNames())
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "BuildingName"), buildingName, BuildingNameAdapter.class, namespaces);
            }

            if (properties.getDepartment() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Department"), properties.getDepartment(), DepartmentAdapter.class, namespaces);

            if (properties.getPostBox() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), properties.getPostBox(), PostBoxAdapter.class, namespaces);

            if (properties.getThoroughfare() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), properties.getThoroughfare(), ThoroughfareAdapter.class, namespaces);

            if (properties.getPostalCode() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), properties.getPostalCode(), PostalCodeAdapter.class, namespaces);
        }
    }
}
