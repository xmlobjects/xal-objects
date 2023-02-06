/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2023 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.PostalDeliveryPoint;
import org.xmlobjects.xal.model.Thoroughfare;

public class DeprecatedPropertiesOfPostOffice extends DeprecatedProperties {
    private Thoroughfare postalRoute;
    private PostalDeliveryPoint postBox;
    private PostCode postalCode;

    public Thoroughfare getPostalRoute() {
        return postalRoute;
    }

    public void setPostalRoute(Thoroughfare postalRoute) {
        this.postalRoute = asChild(postalRoute);
    }

    public PostalDeliveryPoint getPostBox() {
        return postBox;
    }

    public void setPostBox(PostalDeliveryPoint postBox) {
        this.postBox = asChild(postBox);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
