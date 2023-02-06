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
import org.xmlobjects.xal.adapter.deprecated.types.PostalRouteNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PostalRouteNumberAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.ThoroughfareNameOrNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostalRouteAdapter extends AddressObjectAdapter<Thoroughfare> {

    @Override
    public Thoroughfare createObject(QName name, Object parent) throws ObjectBuildException {
        Thoroughfare object = new Thoroughfare();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> object.getOtherAttributes().add("Type", v));
    }

    @Override
    public void buildChildObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
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
                case "PostalRouteName":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(PostalRouteNameAdapter.class)));
                    break;
                case "PostalRouteNumber":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(PostalRouteNumberAdapter.class)));
                    break;
                case "PostBox":
                    object.getDeprecatedProperties().setPostBox(reader.getObjectUsingBuilder(PostBoxAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, Thoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(Thoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        Identifier number = null;
        boolean hasNames = false;

        if (object.isSetNameElementOrNumber()) {
            for (ThoroughfareNameOrNumber nameElementOrNumber : object.getNameElementOrNumber()) {
                if (nameElementOrNumber.isSetNameElement()) {
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalRouteName"), nameElementOrNumber.getNameElement(), PostalRouteNameAdapter.class, namespaces);
                    hasNames = true;
                } else if (!hasNames && number == null)
                    number = nameElementOrNumber.getNumber();
            }
        }

        if (!hasNames && number != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalRouteNumber"), number, PostalRouteNumberAdapter.class, namespaces);

        if (object.hasDeprecatedProperties() && object.getDeprecatedProperties().getPostBox() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostBox"), object.getDeprecatedProperties().getPostBox(), PostBoxAdapter.class, namespaces);
    }
}
