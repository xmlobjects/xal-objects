package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostalRoute extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private List<PostalRouteName> postalRouteNames;
    private PostalRouteNumber postalRouteNumber;
    private PostBox postBox;
    private List<GenericElement> genericElements;
    private String type;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<PostalRouteName> getPostalRouteNames() {
        if (postalRouteNames == null)
            postalRouteNames = new ChildList<>(this);

        return postalRouteNames;
    }

    public boolean isSetPostalRouteNames() {
        return postalRouteNames != null && !postalRouteNames.isEmpty();
    }

    public void setPostalRouteNames(List<PostalRouteName> postalRouteNames) {
        clearChoice();
        this.postalRouteNames = asChild(postalRouteNames);
    }

    public PostalRouteNumber getPostalRouteNumber() {
        return postalRouteNumber;
    }

    public boolean isSetPostalRouteNumber() {
        return postalRouteNumber != null;
    }

    public void setPostalRouteNumber(PostalRouteNumber postalRouteNumber) {
        clearChoice();
        this.postalRouteNumber = asChild(postalRouteNumber);
    }

    public PostBox getPostBox() {
        return postBox;
    }

    public void setPostBox(PostBox postBox) {
        this.postBox = asChild(postBox);
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

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    private void clearChoice() {
        postalRouteNames = null;
        postalRouteNumber = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
