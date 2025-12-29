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

import org.xmlobjects.util.copy.CopyBuilder;
import org.xmlobjects.util.copy.CopyContext;
import org.xmlobjects.util.copy.Copyable;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenericAttributes implements Copyable, Serializable {
    private final Map<String, Map<String, String>> attributes = new HashMap<>();

    public void add(String namespaceURI, String localName, String value) {
        attributes.computeIfAbsent(namespaceURI, v -> new HashMap<>()).put(localName, value);
    }

    public void add(String localName, String value) {
        add(XMLConstants.NULL_NS_URI, localName, value);
    }

    public void add(QName name, String value) {
        add(name.getNamespaceURI(), name.getLocalPart(), value);
    }

    public void addAll(String namespaceURI, Map<String, String> attributes) {
        this.attributes.computeIfAbsent(namespaceURI, v -> new HashMap<>()).putAll(attributes);
    }

    public boolean contains(String localName) {
        return contains(XMLConstants.NULL_NS_URI, localName);
    }

    public boolean contains(QName name) {
        return contains(name.getNamespaceURI(), name.getLocalPart());
    }

    public boolean contains(String namespaceURI, String localName) {
        return get(namespaceURI).containsKey(localName);
    }

    public Map<String, Map<String, String>> get() {
        return attributes;
    }

    public Map<String, String> get(String namespaceURI) {
        return attributes.getOrDefault(namespaceURI, Collections.emptyMap());
    }

    public String getValue(String localName) {
        return getValue(XMLConstants.NULL_NS_URI, localName);
    }

    public String getValue(String namespaceURI, String localName) {
        return get(namespaceURI).getOrDefault(localName, null);
    }

    public String getValue(QName name) {
        return getValue(name.getNamespaceURI(), name.getLocalPart());
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    @Override
    public GenericAttributes deepCopy(CopyBuilder builder, CopyContext context) {
        GenericAttributes copy = new GenericAttributes();
        copy.attributes.putAll(attributes);
        return copy;
    }
}
