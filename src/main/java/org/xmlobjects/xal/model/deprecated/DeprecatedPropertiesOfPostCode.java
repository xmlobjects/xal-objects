/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.deprecated;

import org.xmlobjects.xal.model.Locality;

public class DeprecatedPropertiesOfPostCode extends DeprecatedProperties {
    private Locality postTown;

    public Locality getPostTown() {
        return postTown;
    }

    public void setPostTown(Locality postTown) {
        this.postTown = asChild(postTown);
    }
}
