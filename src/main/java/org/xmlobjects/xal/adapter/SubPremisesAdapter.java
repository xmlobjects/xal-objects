/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.deprecated.types.PremiseNameAdapter;
import org.xmlobjects.xal.model.SubPremises;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubPremises;
import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.PremisesName;
import org.xmlobjects.xal.model.types.SubPremisesType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremisesAdapter extends AbstractPremisesAdapter<SubPremises> {

    @Override
    public SubPremises createObject(QName name, Object parent) throws ObjectBuildException {
        return new SubPremises();
    }

    @Override
    public void initializeObject(SubPremises object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Type").ifPresent(v -> object.setType(SubPremisesType.fromValue(v)));
    }

    @Override
    public void initializeElement(Element element, SubPremises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);

        if (object.getType() != null)
            element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Type", object.getType().toValue());
    }

    @Override
    public void writeChildElements(SubPremises object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        if (object.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfSubPremises properties = object.getDeprecatedProperties();

            if (properties.isSetBuildingNames()) {
                for (Identifier buildingName : properties.getBuildingNames()) {
                    PremisesName nameElement = new PremisesName(buildingName.getContent());
                    writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "NameElement"), nameElement, PremiseNameAdapter.class, namespaces);
                }
            }
        }
    }
}
