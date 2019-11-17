package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostBox extends XALObject {
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
