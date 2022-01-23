/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2021 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.types.PostTownNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PostTownSuffixAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.types.LocalityName;
import org.xmlobjects.xal.model.types.LocalityType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostTownAdapter extends AddressObjectAdapter<Locality> {

    @Override
    public Locality createObject(QName name, Object parent) throws ObjectBuildException {
        Locality object = new Locality(LocalityType.POST_TOWN);
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Locality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> object.getOtherAttributes().add("Type", v));
    }

    @Override
    public void buildChildObject(Locality object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    Address address = object.getParent(Address.class);
                    if (address != null) {
                        if (address.getFreeTextAddress() == null)
                            address.setFreeTextAddress(new FreeTextAddress());

                        address.getFreeTextAddress().getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    }
                    break;
                case "PostTownName":
                    object.getNameElements().add(reader.getObjectUsingBuilder(PostTownNameAdapter.class));
                    break;
                case "PostTownSuffix":
                    object.getDeprecatedProperties().setPostTownSuffix(reader.getObjectUsingBuilder(PostTownSuffixAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, Locality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(Locality object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetNameElements()) {
            for (LocalityName nameElement : object.getNameElements())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostTownName"), nameElement, PostTownNameAdapter.class, namespaces);
        }

        if (object.hasDeprecatedProperties() && object.getDeprecatedProperties().getPostTownSuffix() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostTownSuffix"), object.getDeprecatedProperties().getPostTownSuffix(), PostTownSuffixAdapter.class, namespaces);
    }
}
