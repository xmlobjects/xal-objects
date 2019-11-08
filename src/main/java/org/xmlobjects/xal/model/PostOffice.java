package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostOffice extends XALObject {
    private List<AddressLine> addressLines;
    private List<PostOfficeName> postOfficeNames;
    private PostOfficeNumber postOfficeNumber;
    private PostalRoute postalRoute;
    private PostBox postBox;
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

    public List<PostOfficeName> getPostOfficeNames() {
        if (postOfficeNames == null)
            postOfficeNames = new ChildList<>(this);

        return postOfficeNames;
    }

    public boolean isSetPostOfficeNames() {
        return postOfficeNames != null && !postOfficeNames.isEmpty();
    }

    public void setPostOfficeNames(List<PostOfficeName> postOfficeNames) {
        clearChoice();
        this.postOfficeNames = asChild(postOfficeNames);
    }

    public PostOfficeNumber getPostOfficeNumber() {
        return postOfficeNumber;
    }

    public boolean isSetPostOfficeNumber() {
        return postOfficeNumber != null;
    }

    public void setPostOfficeNumber(PostOfficeNumber postOfficeNumber) {
        clearChoice();
        this.postOfficeNumber = asChild(postOfficeNumber);
    }

    public PostalRoute getPostalRoute() {
        return postalRoute;
    }

    public void setPostalRoute(PostalRoute postalRoute) {
        this.postalRoute = asChild(postalRoute);
    }

    public PostBox getPostBox() {
        return postBox;
    }

    public void setPostBox(PostBox postBox) {
        this.postBox = asChild(postBox);
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

    private void clearChoice() {
        postOfficeNames = null;
        postOfficeNumber = null;
    }
}
