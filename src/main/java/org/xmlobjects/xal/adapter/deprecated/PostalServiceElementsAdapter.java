/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2022 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.AddressIdentifierAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PostalServiceElementAdapter;
import org.xmlobjects.xal.model.PostalServiceElements;
import org.xmlobjects.xal.model.deprecated.types.AddressIdentifier;
import org.xmlobjects.xal.model.deprecated.types.PostalServiceElement;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PostalServiceElementsAdapter extends AddressObjectAdapter<PostalServiceElements> {

    @Override
    public PostalServiceElements createObject(QName name, Object parent) throws ObjectBuildException {
        return new PostalServiceElements();
    }

    @Override
    public void initializeObject(PostalServiceElements object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(v -> object.getOtherAttributes().add("Type", v));
    }

    @Override
    public void buildChildObject(PostalServiceElements object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressIdentifier":
                    object.getAddressIdentifiers().add(reader.getObjectUsingBuilder(AddressIdentifierAdapter.class));
                    break;
                case "EndorsementLineCode":
                    object.setEndorsementLineCode(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "KeyLineCode":
                    object.setKeyLineCode(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "Barcode":
                    object.setBarCode(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "SortingCode":
                    object.setSortingCode(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "AddressLatitude":
                    object.setAddressLatitude(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "AddressLatitudeDirection":
                    object.setAddressLatitudeDirection(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "AddressLongitude":
                    object.setAddressLongitude(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "AddressLongitudeDirection":
                    object.setAddressLongitudeDirection(reader.getObjectUsingBuilder(PostalServiceElementAdapter.class));
                    break;
                case "SupplementaryPostalServiceData":
                    object.getSupplementaryPostalServiceData().add(reader.getObjectUsingBuilder(AddressIdentifierAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, PostalServiceElements object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute("Type", object.getOtherAttributes().getValue("Type"));
    }

    @Override
    public void writeChildElements(PostalServiceElements object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.isSetAddressIdentifiers()) {
            for (AddressIdentifier identifier : object.getAddressIdentifiers())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressIdentifier"), identifier, AddressIdentifierAdapter.class, namespaces);
        }

        if (object.getEndorsementLineCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "EndorsementLineCode"), object.getEndorsementLineCode(), PostalServiceElementAdapter.class, namespaces);

        if (object.getKeyLineCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "KeyLineCode"), object.getKeyLineCode(), PostalServiceElementAdapter.class, namespaces);

        if (object.getBarCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Barcode"), object.getBarCode(), PostalServiceElementAdapter.class, namespaces);

        if (object.getSortingCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SortingCode"), object.getSortingCode(), PostalServiceElementAdapter.class, namespaces);

        if (object.getAddressLatitude() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLatitude"), object.getAddressLatitude(), PostalServiceElementAdapter.class, namespaces);

        if (object.getAddressLatitudeDirection() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLatitudeDirection"), object.getAddressLatitudeDirection(), PostalServiceElementAdapter.class, namespaces);

        if (object.getAddressLongitude() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLongitude"), object.getAddressLongitude(), PostalServiceElementAdapter.class, namespaces);

        if (object.getAddressLongitudeDirection() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLongitudeDirection"), object.getAddressLongitudeDirection(), PostalServiceElementAdapter.class, namespaces);

        if (object.isSetSupplementaryPostalServiceData()) {
            for (PostalServiceElement element : object.getSupplementaryPostalServiceData())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "SupplementaryPostalServiceData"), element, PostalServiceElementAdapter.class, namespaces);
        }
    }
}
