/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public class CountryName extends NameWithCode<CountryNameType> {

    public CountryName() {
        super();
    }

    public CountryName(String content, CountryNameType nameType) {
        super(content, nameType);
    }

    public CountryName(String content) {
        super(content);
    }

    public CountryName(CountryNameType nameType) {
        super(nameType);
    }
}
