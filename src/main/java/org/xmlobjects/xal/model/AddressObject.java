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

package org.xmlobjects.xal.model;

import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.visitor.XALVisitor;
import org.xmlobjects.xml.Attributes;

public abstract class AddressObject extends XALObject {
    private DeprecatedProperties deprecatedProperties;
    private Attributes otherAttributes;

    public abstract void accept(XALVisitor visitor);

    public DeprecatedProperties getDeprecatedProperties() {
        if (deprecatedProperties == null)
            deprecatedProperties = asChild(createDeprecatedProperties());

        return deprecatedProperties;
    }

    public boolean hasDeprecatedProperties() {
        return deprecatedProperties != null;
    }

    protected DeprecatedProperties createDeprecatedProperties() {
        return null;
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
