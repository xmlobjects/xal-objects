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

package org.xmlobjects.xal.adapter.deprecated;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.model.Child;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.BuildingNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseLocationAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberPrefixAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberRangeAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNumberSuffixAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;
import org.xmlobjects.xal.model.types.PremisesType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.namespace.QName;

public class PremiseAdapter extends AddressObjectAdapter<Premises> {

    @Override
    public Premises createObject(QName name, Object parent) throws ObjectBuildException {
        Premises object = new Premises();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Premises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("PremiseDependency").ifPresent(v -> object.getOtherAttributes().add("PremiseDependency", v));
        attributes.getValue("PremiseDependencyType").ifPresent(v -> object.getOtherAttributes().add("PremiseDependencyType", v));
        attributes.getValue("PremiseThoroughfareConnector").ifPresent(v -> object.getOtherAttributes().add("PremiseThoroughfareConnector", v));
        attributes.getValue("Type").ifPresent(v -> {
            PremisesType type = PremisesType.fromValue(v);
            if (type != null)
                object.setType(type);
            else
                object.getOtherAttributes().add("Type", v);
        });
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
                case "PremiseName":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNameAdapter.class)));
                    break;
                case "PremiseLocation":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseLocationAdapter.class)));
                    break;
                case "PremiseNumber":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNumberAdapter.class)));
                    break;
                case "PremiseNumberRange":
                    object.getNameElementOrNumber().addAll(reader.getObjectUsingBuilder(PremiseNumberRangeAdapter.class));
                    break;
                case "PremiseNumberPrefix":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNumberPrefixAdapter.class)));
                    break;
                case "PremiseNumberSuffix":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(PremiseNumberSuffixAdapter.class)));
                    break;
                case "BuildingName":
                    object.getDeprecatedProperties().getBuildingNames().add(reader.getObjectUsingBuilder(BuildingNameAdapter.class));
                    break;
                case "SubPremise":
                    object.getSubPremises().add(reader.getObjectUsingBuilder(SubPremiseAdapter.class));
                    break;
                case "Firm":
                    object.getDeprecatedProperties().setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
                    break;
                case "MailStop":
                    PostalDeliveryPoint mailStop = reader.getObjectUsingBuilder(MailStopAdapter.class);
                    if (address != null && address.getPostalDeliveryPoint() == null)
                        address.setPostalDeliveryPoint(mailStop);
                    else
                        object.getDeprecatedProperties().setMailStop(mailStop);
                    break;
                case "PostalCode":
                    PostCode postalCode = reader.getObjectUsingBuilder(PostalCodeAdapter.class);
                    if (address != null && address.getPostCode() == null)
                        address.setPostCode(postalCode);
                    else
                        object.getDeprecatedProperties().setPostalCode(postalCode);
                    break;
                case "Premise":
                    object.getDeprecatedProperties().setPremise(reader.getObjectUsingBuilder(PremiseAdapter.class));
                    break;
            }
        }
    }
}
