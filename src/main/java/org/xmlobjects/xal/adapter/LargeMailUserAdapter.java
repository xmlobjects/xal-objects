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
import org.xmlobjects.xal.model.BuildingName;
import org.xmlobjects.xal.model.LargeMailUser;
import org.xmlobjects.xal.model.LargeMailUserName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class LargeMailUserAdapter implements ObjectBuilder<LargeMailUser>, ObjectSerializer<LargeMailUser> {

    @Override
    public LargeMailUser createObject(QName name) throws ObjectBuildException {
        return new LargeMailUser();
    }

    @Override
    public void initializeObject(LargeMailUser object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(LargeMailUser object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "LargeMailUserName":
                    object.getLargeMailUserNames().add(reader.getObjectUsingBuilder(LargeMailUserNameAdapter.class));
                    break;
                case "LargeMailUserIdentifier":
                    object.setLargeMailUserIdentifier(reader.getObjectUsingBuilder(LargeMailUserIdentifierAdapter.class));
                    break;
                case "BuildingName":
                    object.getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
                    break;
                case "Department":
                    object.setDepartment(reader.getObjectUsingBuilder(DepartmentAdapter.class));
                    break;
                case "PostBox":
                    object.setPostBox(reader.getObjectUsingBuilder(PostBoxAdapter.class));
                    break;
                case "Thoroughfare":
                    object.setThoroughfare(reader.getObjectUsingBuilder(ThoroughfareAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(LargeMailUser object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUser");
    }

    @Override
    public void initializeElement(Element element, LargeMailUser object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(LargeMailUser object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (LargeMailUserName largeMailUserName : object.getLargeMailUserNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUserName"), largeMailUserName, LargeMailUserNameAdapter.class, namespaces);

        if (object.getLargeMailUserIdentifier() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "LargeMailUserIdentifier"), object.getLargeMailUserIdentifier(), LargeMailUserIdentifierAdapter.class, namespaces);

        for (BuildingName buildingName : object.getBuildingNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "BuildingName"), buildingName, BuildingNameAdapter.class, namespaces);

        if (object.getDepartment() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Department"), object.getDepartment(), DepartmentAdapter.class, namespaces);

        if (object.getPostBox() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), object.getPostBox(), PostBoxAdapter.class, namespaces);

        if (object.getThoroughfare() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), object.getThoroughfare(), ThoroughfareAdapter.class, namespaces);

        if (object.getPostalCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
