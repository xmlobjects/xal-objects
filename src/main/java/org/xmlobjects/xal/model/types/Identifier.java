/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2022 Claus Nagel <claus.nagel@gmail.com>
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

public class Identifier extends XALObject implements Abbreviation {
    private String content;
    private IdentifierElementType type;
    private Boolean abbreviation;
    private Attributes otherAttributes;

    public Identifier() {
    }

    public Identifier(String content, IdentifierElementType type) {
        this.content = content;
        this.type = type;
    }

    public Identifier(String content) {
        this(content, null);
    }

    public Identifier(IdentifierElementType type) {
        this(null, type);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IdentifierElementType getType() {
        return type;
    }

    public void setType(IdentifierElementType type) {
        this.type = type;
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

    public Attributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new Attributes();

        return otherAttributes;
    }

    public boolean isSetOtherAttributes() {
        return otherAttributes != null && !otherAttributes.isEmpty();
    }

    public void setOtherAttributes(Attributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
