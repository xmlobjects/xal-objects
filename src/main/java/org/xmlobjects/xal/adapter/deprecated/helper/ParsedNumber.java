/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.helper;

import org.xmlobjects.xal.model.types.Identifier;
import org.xmlobjects.xal.model.types.IdentifierElementType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParsedNumber {
    private IdentifierElementType type;
    private List<Identifier> prefixes;
    private List<Identifier> numbers;
    private List<Identifier> suffixes;

    IdentifierElementType getType() {
        return type;
    }

    public List<Identifier> getPrefixes() {
        return prefixes != null ? prefixes : Collections.emptyList();
    }

    public List<Identifier> getNumbers() {
        return numbers != null ? numbers : Collections.emptyList();
    }

    public List<Identifier> getSuffixes() {
        return suffixes != null ? suffixes : Collections.emptyList();
    }

    void add(Identifier identifier) {
        IdentifierElementType type = identifier.getType();

        if (type == IdentifierElementType.PREFIX) {
            if (prefixes == null)
                prefixes = new ArrayList<>();

            prefixes.add(identifier);
        } else if (type == IdentifierElementType.SUFFIX) {
            if (suffixes == null)
                suffixes = new ArrayList<>();

            suffixes.add(identifier);
        } else {
            if (numbers == null) {
                numbers = new ArrayList<>();
                this.type = type;
            }

            numbers.add(identifier);
        }
    }

    boolean accepts(Identifier identifier) {
        IdentifierElementType type = identifier.getType();

        if (type == IdentifierElementType.PREFIX)
            return numbers == null;
        else if (type == IdentifierElementType.SUFFIX)
            return numbers != null || suffixes != null;
        else
            return suffixes == null && (numbers == null || this.type == type);
    }
}
