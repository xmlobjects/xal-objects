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

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostBox extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private PostBoxNumber postBoxNumber;
    private PostBoxNumberPrefix postBoxNumberPrefix;
    private PostBoxNumberSuffix postBoxNumberSuffix;
    private PostBoxNumberExtension postBoxNumberExtension;
    private Firm firm;
    private PostalCode postalCode;
    private List<GenericElement> genericElements;
    private String type;
    private String indicator;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public PostBoxNumber getPostBoxNumber() {
        return postBoxNumber;
    }

    public void setPostBoxNumber(PostBoxNumber postBoxNumber) {
        this.postBoxNumber = asChild(postBoxNumber);
    }

    public PostBoxNumberPrefix getPostBoxNumberPrefix() {
        return postBoxNumberPrefix;
    }

    public void setPostBoxNumberPrefix(PostBoxNumberPrefix postBoxNumberPrefix) {
        this.postBoxNumberPrefix = asChild(postBoxNumberPrefix);
    }

    public PostBoxNumberSuffix getPostBoxNumberSuffix() {
        return postBoxNumberSuffix;
    }

    public void setPostBoxNumberSuffix(PostBoxNumberSuffix postBoxNumberSuffix) {
        this.postBoxNumberSuffix = asChild(postBoxNumberSuffix);
    }

    public PostBoxNumberExtension getPostBoxNumberExtension() {
        return postBoxNumberExtension;
    }

    public void setPostBoxNumberExtension(PostBoxNumberExtension postBoxNumberExtension) {
        this.postBoxNumberExtension = asChild(postBoxNumberExtension);
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = asChild(firm);
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = asChild(postalCode);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
