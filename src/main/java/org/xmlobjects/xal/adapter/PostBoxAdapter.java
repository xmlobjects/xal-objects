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
import org.xmlobjects.xal.model.PostBox;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "PostBox", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class PostBoxAdapter implements ObjectBuilder<PostBox>, ObjectSerializer<PostBox> {

    @Override
    public PostBox createObject(QName name) throws ObjectBuildException {
        return new PostBox();
    }

    @Override
    public void initializeObject(PostBox object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(PostBox object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "PostBoxNumber":
                    object.setPostBoxNumber(reader.getObjectUsingBuilder(PostBoxNumberAdapter.class));
                    break;
                case "PostBoxNumberPrefix":
                    object.setPostBoxNumberPrefix(reader.getObjectUsingBuilder(PostBoxNumberPrefixAdapter.class));
                    break;
                case "PostBoxNumberSuffix":
                    object.setPostBoxNumberSuffix(reader.getObjectUsingBuilder(PostBoxNumberSuffixAdapter.class));
                    break;
                case "PostBoxNumberExtension":
                    object.setPostBoxNumberExtension(reader.getObjectUsingBuilder(PostBoxNumberExtensionAdapter.class));
                    break;
                case "Firm":
                    object.setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
                    break;
                case "PostalCode":
                    object.setPostalCode(reader.getObjectUsingBuilder(PostalCodeAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(PostBox object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox");
    }

    @Override
    public void initializeElement(Element element, PostBox object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("Type", object.getType());
        element.addAttribute("Indicator", object.getIndicator());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(PostBox object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        if (object.getPostBoxNumber() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumber"), object.getPostBoxNumber(), PostBoxNumberAdapter.class, namespaces);

        if (object.getPostBoxNumberPrefix() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberPrefix"), object.getPostBoxNumberPrefix(), PostBoxNumberPrefixAdapter.class, namespaces);

        if (object.getPostBoxNumberSuffix() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberSuffix"), object.getPostBoxNumberSuffix(), PostBoxNumberSuffixAdapter.class, namespaces);

        if (object.getPostBoxNumberExtension() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBoxNumberExtension"), object.getPostBoxNumberExtension(), PostBoxNumberExtensionAdapter.class, namespaces);

        if (object.getFirm() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Firm"), object.getFirm(), FirmAdapter.class, namespaces);

        if (object.getPostalCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), object.getPostalCode(), PostalCodeAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
