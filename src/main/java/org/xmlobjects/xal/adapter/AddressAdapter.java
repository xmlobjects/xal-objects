/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2024 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.types.AddressType;
import org.xmlobjects.xal.model.types.AddressUsage;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

@XMLElement(name = "Address", namespaceURI = XALConstants.XAL_3_0_NAMESPACE)
public class AddressAdapter extends AddressObjectAdapter<Address> {

    @Override
    public Address createObject(QName name, Object parent) throws ObjectBuildException {
        return new Address();
    }

    @Override
    public void initializeObject(Address object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(AddressType.fromValue(v)));
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "AddressID").collapse().ifPresent(object::setAddressId);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "AddressIDType").normalize().ifPresent(object::setAddressIdType);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "ID").collapse().ifPresent(object::setAddressId);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Usage").ifPresent(v -> object.setUsage(AddressUsage.fromValue(v)));
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "DeliveryMode").normalize().ifPresent(object::setDeliveryMode);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Status").ifPresent(object::setStatus);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "AddressKey").collapse().ifPresent(object::setAddressKey);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "AddressKeyRef").collapse().ifPresent(object::setAddressKeyRef);
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "DateValidFrom").ifDateTime(object::setDateValidFrom);
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "DateValidTo").ifDateTime(object::setDateValidTo);
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "LanguageCode").ifPresent(object::setLanguageCode);
        attributes.getValue(XALConstants.XLINK_NAMESPACE, "type").ifPresent(object::setXlinkType);
        attributes.getValue(XALConstants.XLINK_NAMESPACE, "label").ifPresent(object::setXlinkLabel);
        attributes.getValue(XALConstants.XLINK_NAMESPACE, "href").ifPresent(object::setXlinkHRef);
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(Address object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "FreeTextAddress":
                    object.setFreeTextAddress(reader.getObjectUsingBuilder(FreeTextAddressAdapter.class));
                    break;
                case "Country":
                    object.setCountry(reader.getObjectUsingBuilder(CountryAdapter.class));
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
                case "Premises":
                    object.setPremises(reader.getObjectUsingBuilder(PremisesAdapter.class));
                    break;
                case "PostCode":
                    object.setPostCode(reader.getObjectUsingBuilder(PostCodeAdapter.class));
                    break;
                case "RuralDelivery":
                    object.setRuralDelivery(reader.getObjectUsingBuilder(RuralDeliveryAdapter.class));
                    break;
                case "PostalDeliveryPoint":
                    object.setPostalDeliveryPoint(reader.getObjectUsingBuilder(PostalDeliveryPointAdapter.class));
                    break;
                case "PostOffice":
                    object.setPostOffice(reader.getObjectUsingBuilder(PostOfficeAdapter.class));
                    break;
                case "GeoRSS":
                    object.setGeoRSS(reader.getObjectUsingBuilder(GeoRSSAdapter.class));
                    break;
                case "LocationByCoordinates":
                    object.setLocationByCoordinates(reader.getObjectUsingBuilder(LocationByCoordinatesAdapter.class));
                    break;
            }
        }
    }

    @Override
    public Element createElement(Address object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_3_0_NAMESPACE, "Address");
    }

    @Override
    public void initializeElement(Element element, Address object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "AddressID", TextContent.of(object.getAddressId()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "AddressIDType", TextContent.of(object.getAddressIdType()).normalize());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "ID", TextContent.of(object.getId()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "DeliveryMode", TextContent.of(object.getDeliveryMode()).normalize());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Status", object.getStatus());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "AddressKey", TextContent.of(object.getAddressKey()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "AddressKeyRef", TextContent.of(object.getAddressKeyRef()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "DateValidFrom", TextContent.ofDateTime(object.getDateValidFrom()));
        element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "DateValidTo", TextContent.ofDateTime(object.getDateValidTo()));
        element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "LanguageCode", object.getLanguageCode());
        element.addAttribute(XALConstants.XLINK_NAMESPACE, "type", object.getXlinkType());
        element.addAttribute(XALConstants.XLINK_NAMESPACE, "label", object.getXlinkLabel());
        element.addAttribute(XALConstants.XLINK_NAMESPACE, "href", object.getXlinkHRef());
        XALSerializerHelper.addDataQualityAttributes(element, object);

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());

        if (object.getUsage() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Usage", object.getUsage().toValue());
    }

    @Override
    public void writeChildElements(Address object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.getFreeTextAddress() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "FreeTextAddress"), object.getFreeTextAddress(), FreeTextAddressAdapter.class, namespaces);

        if (object.getCountry() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Country"), object.getCountry(), CountryAdapter.class, namespaces);

        if (object.getAdministrativeArea() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "AdministrativeArea"), object.getAdministrativeArea(), AdministrativeAreaAdapter.class, namespaces);

        if (object.getLocality() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Locality"), object.getLocality(), LocalityAdapter.class, namespaces);

        if (object.getThoroughfare() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Thoroughfare"), object.getThoroughfare(), ThoroughfareAdapter.class, namespaces);

        if (object.getPremises() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Premises"), object.getPremises(), PremisesAdapter.class, namespaces);

        if (object.getPostCode() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "PostCode"), object.getPostCode(), PostCodeAdapter.class, namespaces);

        if (object.getRuralDelivery() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "RuralDelivery"), object.getRuralDelivery(), RuralDeliveryAdapter.class, namespaces);

        if (object.getPostalDeliveryPoint() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "PostalDeliveryPoint"), object.getPostalDeliveryPoint(), PostalDeliveryPointAdapter.class, namespaces);

        if (object.getPostOffice() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "PostOffice"), object.getPostOffice(), PostOfficeAdapter.class, namespaces);

        if (object.getGeoRSS() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "GeoRSS"), object.getGeoRSS(), GeoRSSAdapter.class, namespaces);

        if (object.getLocationByCoordinates() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "LocationByCoordinates"), object.getLocationByCoordinates(), LocationByCoordinatesAdapter.class, namespaces);
    }
}
