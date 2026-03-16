/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.XALObject;

public class Identifier extends XALObject implements Abbreviation {
    private String content;
    private IdentifierElementType type;
    private Boolean abbreviation;
    private GenericAttributes otherAttributes;

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

    public GenericAttributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new GenericAttributes();

        return otherAttributes;
    }

    public boolean isSetOtherAttributes() {
        return otherAttributes != null && !otherAttributes.isEmpty();
    }

    public void setOtherAttributes(GenericAttributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
