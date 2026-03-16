/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.XALObject;

public abstract class Name<T extends NameType> extends XALObject implements Abbreviation {
    private String content;
    private Boolean abbreviation;
    private T nameType;
    private GenericAttributes otherAttributes;

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
