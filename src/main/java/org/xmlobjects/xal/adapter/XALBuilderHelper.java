/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;

import javax.xml.XMLConstants;
import java.util.function.Supplier;

public class XALBuilderHelper {

    public static void buildDataQualityAttributes(DataQuality object, Attributes attributes) {
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "DataQualityType").ifPresent(v -> object.setDataQualityType(DataQualityType.fromValue(v)));
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "ValidFrom").ifDateTime(object::setValidFrom);
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "ValidTo").ifDateTime(object::setValidTo);
    }

    public static void buildOtherAttributes(Supplier<GenericAttributes> otherAttributes, Attributes attributes) {
        attributes.forEach((namespaceURI, localName, value) -> {
            if (!XALConstants.XAL_3_0_NAMESPACE.equals(namespaceURI)
                    && !XALConstants.XAL_3_0_CT_NAMESPACE.equals(namespaceURI)
                    && !XALConstants.XAL_2_0_NAMESPACE.equals(namespaceURI)
                    && !XMLConstants.NULL_NS_URI.equals(namespaceURI)
                    && !XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI.equals(namespaceURI)) {
                otherAttributes.get().add(namespaceURI, localName, value.get());
            }
        });
    }
}
