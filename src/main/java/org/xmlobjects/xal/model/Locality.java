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

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.LocalityName;
import org.xmlobjects.xal.model.types.LocalityType;

import javax.xml.namespace.QName;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locality extends XALObject implements DataQuality {
    private List<LocalityName> nameElements;
    private SubLocality subLocality;
    private LocalityType type;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private Map<QName, String> otherAttributes;

    public List<LocalityName> getNameElements() {
        if (nameElements == null)
            nameElements = new ChildList<>(this);

        return nameElements;
    }

    public void setNameElements(List<LocalityName> nameElements) {
        this.nameElements = asChild(nameElements);
    }

    public SubLocality getSubLocality() {
        return subLocality;
    }

    public void setSubLocality(SubLocality subLocality) {
        this.subLocality = asChild(subLocality);
    }

    public LocalityType getType() {
        return type;
    }

    public void setType(LocalityType type) {
        this.type = type;
    }

    @Override
    public DataQualityType getDataQualityType() {
        return dataQualityType;
    }

    @Override
    public void setDataQualityType(DataQualityType dataQualityType) {
        this.dataQualityType = dataQualityType;
    }

    @Override
    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public OffsetDateTime getValidTo() {
        return validTo;
    }

    @Override
    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
