package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostalCode extends XALObject {
    private List<AddressLine> addressLines;
    private List<PostalCodeNumber> postalCodeNumbers;
    private List<PostalCodeNumberExtension> postalCodeNumberExtensions;
    private PostTown postTown;
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

    public List<PostalCodeNumber> getPostalCodeNumbers() {
        if (postalCodeNumbers == null)
            postalCodeNumbers = new ChildList<>(this);

        return postalCodeNumbers;
    }

    public void setPostalCodeNumbers(List<PostalCodeNumber> postalCodeNumbers) {
        this.postalCodeNumbers = asChild(postalCodeNumbers);
    }

    public List<PostalCodeNumberExtension> getPostalCodeNumberExtensions() {
        if (postalCodeNumberExtensions == null)
            postalCodeNumberExtensions = new ChildList<>(this);

        return postalCodeNumberExtensions;
    }

    public void setPostalCodeNumberExtensions(List<PostalCodeNumberExtension> postalCodeNumberExtensions) {
        this.postalCodeNumberExtensions = asChild(postalCodeNumberExtensions);
    }

    public PostTown getPostTown() {
        return postTown;
    }

    public void setPostTown(PostTown postTown) {
        this.postTown = asChild(postTown);
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

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
