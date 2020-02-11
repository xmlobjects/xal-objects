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
import org.xmlobjects.xal.model.PostalCode;
import org.xmlobjects.xal.model.PostalCodeNumber;
import org.xmlobjects.xal.model.PostalCodeNumberExtension;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "PostalCode", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class PostalCodeAdapter implements ObjectBuilder<PostalCode>, ObjectSerializer<PostalCode> {

    @Override
    public PostalCode createObject(QName name) throws ObjectBuildException {
        return new PostalCode();
    }

    @Override
    public void initializeObject(PostalCode object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(PostalCode object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "PostalCodeNumber":
                    object.getPostalCodeNumbers().add(reader.getObjectUsingBuilder(PostalCodeNumberAdapter.class));
                    break;
                case "PostalCodeNumberExtension":
                    object.getPostalCodeNumberExtensions().add(reader.getObjectUsingBuilder(PostalCodeNumberExtensionAdapter.class));
                    break;
                case "PostTown":
                    object.setPostTown(reader.getObjectUsingBuilder(PostTownAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(PostalCode object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode");
    }

    @Override
    public void initializeElement(Element element, PostalCode object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(PostalCode object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (PostalCodeNumber postalCodeNumber : object.getPostalCodeNumbers())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCodeNumber"), postalCodeNumber, PostalCodeNumberAdapter.class, namespaces);

        for (PostalCodeNumberExtension postalCodeNumberExtension : object.getPostalCodeNumberExtensions())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCodeNumberExtension"), postalCodeNumberExtension, PostalCodeNumberExtensionAdapter.class, namespaces);

        if (object.getPostTown() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostTown"), object.getPostTown(), PostTownAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
