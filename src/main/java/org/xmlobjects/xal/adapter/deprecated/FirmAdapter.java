/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.adapter.deprecated.types.DepartmentNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.FirmNameAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.SubPremises;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfPremises;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class FirmAdapter extends AddressObjectAdapter<Premises> {

    @Override
    public Premises createObject(QName name, Object parent) throws ObjectBuildException {
        Premises object = new Premises();
        if (parent instanceof Child child)
            object.setParent(child);

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
                case "FirmName":
                    object.getNameElementOrNumber().add(new PremisesNameOrNumber(reader.getObjectUsingBuilder(FirmNameAdapter.class)));
                    break;
                case "Department":
                    object.getSubPremises().add(reader.getObjectUsingBuilder(DepartmentAdapter.class));
                    break;
                case "MailStop":
                    object.getDeprecatedProperties().setMailStop(reader.getObjectUsingBuilder(MailStopAdapter.class));
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
        if (object.isSetNameElementOrNumber()) {
            for (PremisesNameOrNumber nameElementOrNumber : object.getNameElementOrNumber()) {
                if (nameElementOrNumber.isSetNameElement())
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "FirmName"), nameElementOrNumber.getNameElement(), DepartmentNameAdapter.class, namespaces);
            }
        }

        if (object.isSetSubPremises()) {
            for (SubPremises subPremises : object.getSubPremises())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Department"), subPremises, DepartmentAdapter.class, namespaces);
        }

        if (object.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPremises properties = object.getDeprecatedProperties();

            if (properties.getMailStop() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "MailStop"), properties.getMailStop(), MailStopAdapter.class, namespaces);

            if (properties.getPostalCode() != null)
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalCode"), properties.getPostalCode(), PostalCodeAdapter.class, namespaces);
        }
    }
}
