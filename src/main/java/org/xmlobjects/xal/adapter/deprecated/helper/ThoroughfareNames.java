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

package org.xmlobjects.xal.adapter.deprecated.helper;

import org.xmlobjects.xal.model.AbstractThoroughfare;
import org.xmlobjects.xal.model.types.ThoroughfareName;
import org.xmlobjects.xal.model.types.ThoroughfareNameOrNumber;
import org.xmlobjects.xal.model.types.ThoroughfareNameType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThoroughfareNames {
    private ThoroughfareName preDirection;
    private List<ThoroughfareName> names;
    private ThoroughfareName postDirection;

    public static ThoroughfareNames of(AbstractThoroughfare thoroughfare) {
        ThoroughfareNames names = new ThoroughfareNames();

        for (ThoroughfareNameOrNumber nameElementOrNumber : thoroughfare.getNameElementOrNumber()) {
            if (nameElementOrNumber.isSetNameElement()) {
                ThoroughfareName nameElement = nameElementOrNumber.getNameElement();
                if (nameElement.getNameType() == ThoroughfareNameType.PRE_DIRECTION && names.preDirection == null)
                    names.preDirection = nameElement;
                else if (nameElement.getNameType() == ThoroughfareNameType.POST_DIRECTION && names.postDirection == null)
                    names.postDirection = nameElement;
                else {
                    if (names.names == null)
                        names.names = new ArrayList<>();

                    names.names.add(nameElement);
                }
            }
        }

        return names;
    }

    public ThoroughfareName getPreDirection() {
        return preDirection;
    }

    public List<ThoroughfareName> getNames() {
        return names != null ? names : Collections.emptyList();
    }

    public ThoroughfareName getPostDirection() {
        return postDirection;
    }
}
