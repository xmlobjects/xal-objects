package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firm extends XALObject {
    private List<AddressLine> addressLines;
    private List<FirmName> firmNames;
    private List<Department> departments;
    private MailStop mailStop;
    private PostalCode postalCode;
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

    public List<FirmName> getFirmNames() {
        if (firmNames == null)
            firmNames = new ChildList<>(this);

        return firmNames;
    }

    public void setFirmNames(List<FirmName> firmNames) {
        this.firmNames = asChild(firmNames);
    }

    public List<Department> getDepartments() {
        if (departments == null)
            departments = new ChildList<>(this);

        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = asChild(departments);
    }

    public MailStop getMailStop() {
        return mailStop;
    }

    public void setMailStop(MailStop mailStop) {
        this.mailStop = asChild(mailStop);
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
