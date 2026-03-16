/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated.types;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.XALObject;

public class PostalServiceElement extends XALObject implements Postal {
    private String content;
    private String type;
    private String code;
    private GenericAttributes otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
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
