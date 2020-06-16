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

package org.xmlobjects.xal.adapter;

import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;
import java.util.Map;

public class XALSerializerHelper {

    public static void addDataQualityAttributes(Element element, DataQuality object) {
        element.addAttribute(XALConstants.CT_3_0_NAMESPACE, "ValidFrom", TextContent.ofDateTime(object.getValidFrom()));
        element.addAttribute(XALConstants.CT_3_0_NAMESPACE, "ValidTo", TextContent.ofDateTime(object.getValidTo()));

        if (object.getDataQualityType() != null)
            element.addAttribute(XALConstants.CT_3_0_NAMESPACE, "DataQualityType", object.getDataQualityType().toValue());
    }

    public static void addOtherAttributes(Element element, Map<QName, String> otherAttributes, Namespaces namespaces) {
        for (Map.Entry<QName, String> entry : otherAttributes.entrySet()) {
            if (namespaces.contains(entry.getKey().getNamespaceURI()))
                element.addAttribute(entry.getKey(), entry.getValue());
        }
    }
}
