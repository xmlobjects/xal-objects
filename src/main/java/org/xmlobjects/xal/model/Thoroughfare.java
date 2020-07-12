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
import org.xmlobjects.xal.model.deprecated.DeprecatedProperties;
import org.xmlobjects.xal.model.deprecated.DeprecatedPropertiesOfThoroughfare;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.util.List;

public class Thoroughfare extends AbstractThoroughfare {
    private List<SubThoroughfare> subThoroughfares;

    public List<SubThoroughfare> getSubThoroughfares() {
        if (subThoroughfares == null)
            subThoroughfares = new ChildList<>(this);

        return subThoroughfares;
    }

    public void setSubThoroughfares(List<SubThoroughfare> subThoroughfares) {
        this.subThoroughfares = asChild(subThoroughfares);
    }

    @Override
    public DeprecatedPropertiesOfThoroughfare getDeprecatedProperties() {
        return (DeprecatedPropertiesOfThoroughfare) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedProperties createDeprecatedProperties() {
        return new DeprecatedPropertiesOfThoroughfare();
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
