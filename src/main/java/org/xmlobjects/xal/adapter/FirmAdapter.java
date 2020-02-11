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
import org.xmlobjects.xal.model.Department;
import org.xmlobjects.xal.model.Firm;
import org.xmlobjects.xal.model.FirmName;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class FirmAdapter implements ObjectBuilder<Firm>, ObjectSerializer<Firm> {

    @Override
    public Firm createObject(QName name) throws ObjectBuildException {
        return new Firm();
    }

    @Override
    public void initializeObject(Firm object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(Firm object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "FirmName":
                    object.getFirmNames().add(reader.getObjectUsingBuilder(FirmNameAdapter.class));
                    break;
                case "Department":
                    object.getDepartments().add(reader.getObjectUsingBuilder(DepartmentAdapter.class));
                    break;
                case "MailStop":
                    object.setMailStop(reader.getObjectUsingBuilder(MailStopAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(Firm object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm");
    }

    @Override
    public void initializeElement(Element element, Firm object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(Firm object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (FirmName firmName : object.getFirmNames())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "FirmName"), firmName, FirmNameAdapter.class, namespaces);

        for (Department department : object.getDepartments())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Department"), department, DepartmentAdapter.class, namespaces);

        if (object.getMailStop() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStop"), object.getMailStop(), MailStopAdapter.class, namespaces);

        if (object.getPostalCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
