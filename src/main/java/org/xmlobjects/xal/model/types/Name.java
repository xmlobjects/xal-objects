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

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.XALObject;
import org.xmlobjects.xml.Attributes;

public abstract class Name<T extends NameType> extends XALObject implements Abbreviation {
    private String content;
    private Boolean abbreviation;
    private T nameType;
    private Attributes otherAttributes;

    public Name() {
    }

    public Name(String content, T nameType) {
        this.content = content;
        this.nameType = nameType;
    }

    public Name(String content) {
        this(content, null);
    }

    public Name(T nameType) {
        this(null, nameType);
    }

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

    public Attributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new Attributes();

        return otherAttributes;
    }

    public void setOtherAttributes(Attributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
