/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2021 Claus Nagel <claus.nagel@gmail.com>
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

    public static void buildOtherAttributes(Supplier<Attributes> otherAttributes, Attributes attributes) {
        for (Map.Entry<String, Map<String, TextContent>> entry : attributes.get().entrySet()) {
            if (!XALConstants.XAL_3_0_NAMESPACE.equals(entry.getKey())
                    && !XALConstants.XAL_3_0_CT_NAMESPACE.equals(entry.getKey())
                    && !XALConstants.XAL_2_0_NAMESPACE.equals(entry.getKey())
                    && !XMLConstants.NULL_NS_URI.equals(entry.getKey())
                    && !XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI.equals(entry.getKey()))
                otherAttributes.get().addAll(entry.getKey(), entry.getValue());
        }
    }
}
