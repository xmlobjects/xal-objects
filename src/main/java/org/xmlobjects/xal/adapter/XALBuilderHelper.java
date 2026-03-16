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
import org.xmlobjects.xml.TextContent;

import javax.xml.XMLConstants;
import java.util.Map;
import java.util.function.Supplier;

public class XALBuilderHelper {

    public static void buildDataQualityAttributes(DataQuality object, Attributes attributes) {
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "DataQualityType").ifPresent(v -> object.setDataQualityType(DataQualityType.fromValue(v)));
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "ValidFrom").ifDateTime(object::setValidFrom);
        attributes.getValue(XALConstants.XAL_3_0_CT_NAMESPACE, "ValidTo").ifDateTime(object::setValidTo);
    }

    public static void buildOtherAttributes(Supplier<GenericAttributes> otherAttributes, Attributes attributes) {
        for (Map.Entry<String, Map<String, TextContent>> entry : attributes.get().entrySet()) {
            if (!XALConstants.XAL_3_0_NAMESPACE.equals(entry.getKey())
                    && !XALConstants.XAL_3_0_CT_NAMESPACE.equals(entry.getKey())
                    && !XALConstants.XAL_2_0_NAMESPACE.equals(entry.getKey())
                    && !XMLConstants.NULL_NS_URI.equals(entry.getKey())
                    && !XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI.equals(entry.getKey())) {
                String namespaceURI = entry.getKey();
                for (Map.Entry<String, TextContent> attribute : entry.getValue().entrySet()) {
                    otherAttributes.get().add(namespaceURI, attribute.getKey(), attribute.getValue().get());
                }
            }
        }
    }
}
