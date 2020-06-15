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

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.XALObject;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public abstract class Name<T extends Enum<?>> extends XALObject implements Abbreviation {
    private String content;
    private Boolean abbreviation;
    private T nameType;
    private Map<QName, String> otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Boolean getAbbreviation() {
        return abbreviation;
    }

    @Override
    public boolean isSetAbbreviation() {
        return abbreviation != null;
    }

    @Override
    public void setAbbreviation(Boolean abbreviation) {
        this.abbreviation = abbreviation;
    }

    public T getNameType() {
        return nameType;
    }

    public void setNameType(T nameType) {
        this.nameType = nameType;
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
