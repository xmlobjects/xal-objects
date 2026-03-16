/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

public abstract class NameWithCode<T extends NameType> extends Name<T> {
    private String nameCode;
    private String nameCodeType;

    public NameWithCode() {
        super();
    }

    public NameWithCode(String content, T nameType) {
        super(content, nameType);
    }

    public NameWithCode(String content) {
        super(content);
    }

    public NameWithCode(T nameType) {
        super(nameType);
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getNameCodeType() {
        return nameCodeType;
    }

    public void setNameCodeType(String nameCodeType) {
        this.nameCodeType = nameCodeType;
    }
}
