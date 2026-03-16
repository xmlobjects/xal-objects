/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.PostalServiceElements;

public class DeprecatedPropertiesOfAddress extends DeprecatedProperties {
    private PostalServiceElements postalServiceElements;

    public PostalServiceElements getPostalServiceElements() {
        return postalServiceElements;
    }

    public void setPostalServiceElements(PostalServiceElements postalServiceElements) {
        this.postalServiceElements = asChild(postalServiceElements);
    }
}
