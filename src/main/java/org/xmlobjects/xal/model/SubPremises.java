/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2024 Claus Nagel <claus.nagel@gmail.com>
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
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfSubPremises;
import org.xmlobjects.xal.model.types.SubPremisesType;
import org.xmlobjects.xal.visitor.XALVisitor;

public class SubPremises extends AbstractPremises {
    private SubPremisesType type;

    public SubPremises() {
    }

    public SubPremises(SubPremisesType type) {
        this.type = type;
    }

    public SubPremisesType getType() {
        return type;
    }

    public void setType(SubPremisesType type) {
        this.type = type;
    }

    @Override
    public DeprecatedPropertiesOfSubPremises getDeprecatedProperties() {
        return (DeprecatedPropertiesOfSubPremises) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfSubPremises();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
