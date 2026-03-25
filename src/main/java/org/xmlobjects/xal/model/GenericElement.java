/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model;

import org.w3c.dom.Element;
import org.xmlobjects.copy.CopyContext;
import org.xmlobjects.copy.CopyMode;
import org.xmlobjects.copy.Copyable;

import java.util.Objects;

public class GenericElement extends XALObject implements Copyable<GenericElement> {
    private final Element content;

    private GenericElement(Element content) {
        this.content = Objects.requireNonNull(content, "Content must not be null.");
    }

    public static GenericElement of(Element element) {
        return new GenericElement(element);
    }

    public Element getContent() {
        return content;
    }

    public String getLocalName() {
        return content.getLocalName();
    }

    public String getNamespaceURI() {
        return content.getNamespaceURI();
    }

    @Override
    public GenericElement newInstance(CopyMode mode, CopyContext context) {
        return switch (mode) {
            case SHALLOW -> new GenericElement(content);
            case DEEP -> new GenericElement((Element) content.cloneNode(true));
        };
    }
}
