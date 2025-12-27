/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2025 Claus Nagel <claus.nagel@gmail.com>
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
