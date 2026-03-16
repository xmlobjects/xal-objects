/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import java.util.Map;

public class XALSerializerHelper {

    public static void addDataQualityAttributes(Element element, DataQuality object) {
        element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "ValidFrom", TextContent.ofDateTime(object.getValidFrom()));
        element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "ValidTo", TextContent.ofDateTime(object.getValidTo()));

        if (object.getDataQualityType() != null)
            element.addAttribute(XALConstants.XAL_3_0_CT_NAMESPACE, "DataQualityType", object.getDataQualityType().toValue());
    }

    public static void addOtherAttributes(Element element, GenericAttributes otherAttributes, Namespaces namespaces) {
        for (Map.Entry<String, Map<String, String>> entry : otherAttributes.get().entrySet()) {
            if (namespaces.contains(entry.getKey())) {
                for (Map.Entry<String, String> attribute : entry.getValue().entrySet())
                    element.addAttribute(entry.getKey(), attribute.getKey(), attribute.getValue());
            }
        }
    }
}
