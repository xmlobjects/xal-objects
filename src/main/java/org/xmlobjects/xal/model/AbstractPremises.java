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

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.model.types.PremisesNameOrNumber;

import java.util.List;

public abstract class AbstractPremises extends AddressObject {
    private List<PremisesNameOrNumber> nameElementOrNumber;
    private String typeCode;

    public List<PremisesNameOrNumber> getNameElementOrNumber() {
        if (nameElementOrNumber == null)
            nameElementOrNumber = new ChildList<>(this);

        return nameElementOrNumber;
    }

    public boolean isSetNameElementOrNumber() {
        return nameElementOrNumber != null && !nameElementOrNumber.isEmpty();
    }

    public void setNameElementOrNumber(List<PremisesNameOrNumber> nameElementOrNumber) {
        this.nameElementOrNumber = asChild(nameElementOrNumber);
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
