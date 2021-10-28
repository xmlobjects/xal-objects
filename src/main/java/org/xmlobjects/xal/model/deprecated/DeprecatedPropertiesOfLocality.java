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

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.*;
import org.xmlobjects.xal.model.types.Identifier;

public class DeprecatedPropertiesOfLocality extends DeprecatedProperties {
    private Identifier postTownSuffix;
    private Premises largeMailUser;
    private Thoroughfare postalRoute;
    private PostalDeliveryPoint postBox;
    private PostOffice postOffice;
    private Thoroughfare thoroughfare;
    private Premises premise;
    private PostCode postalCode;

    public Identifier getPostTownSuffix() {
        return postTownSuffix;
    }

    public void setPostTownSuffix(Identifier postTownSuffix) {
        this.postTownSuffix = asChild(postTownSuffix);
    }

    public Premises getLargeMailUser() {
        return largeMailUser;
    }

    public void setLargeMailUser(Premises largeMailUser) {
        this.largeMailUser = asChild(largeMailUser);
    }

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

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = asChild(postOffice);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        this.thoroughfare = asChild(thoroughfare);
    }

    public Premises getPremise() {
        return premise;
    }

    public void setPremise(Premises premise) {
        this.premise = asChild(premise);
    }

    public PostCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostCode postalCode) {
        this.postalCode = asChild(postalCode);
    }
}
