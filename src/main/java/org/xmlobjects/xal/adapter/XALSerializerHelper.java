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

package org.xmlobjects.xal.adapter;

import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
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

    public static void addOtherAttributes(Element element, Attributes otherAttributes, Namespaces namespaces) {
        for (Map.Entry<String, Map<String, TextContent>> entry : otherAttributes.get().entrySet()) {
            if (namespaces.contains(entry.getKey())) {
                for (Map.Entry<String, TextContent> attribute : entry.getValue().entrySet())
                    element.addAttribute(entry.getKey(), attribute.getKey(), attribute.getValue());
            }
        }
    }
}
