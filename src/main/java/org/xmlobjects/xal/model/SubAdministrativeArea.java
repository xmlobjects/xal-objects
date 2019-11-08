package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubAdministrativeArea extends XALObject {
    private List<AddressLine> addressLines;
    private List<SubAdministrativeAreaName> subAdministrativeAreaNames;
    private Locality locality;
    private PostOffice postOffice;
    private PostalCode postalCode;
    private List<GenericElement> genericElements;
    private String type;
    private String usageType;
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

    public List<SubAdministrativeAreaName> getSubAdministrativeAreaNames() {
        if (subAdministrativeAreaNames == null)
            subAdministrativeAreaNames = new ChildList<>(this);

        return subAdministrativeAreaNames;
    }

    public void setSubAdministrativeAreaNames(List<SubAdministrativeAreaName> subAdministrativeAreaNames) {
        this.subAdministrativeAreaNames = asChild(subAdministrativeAreaNames);
    }

    public Locality getLocality() {
        return locality;
    }

    public boolean isSetLocality() {
        return locality != null;
    }

    public void setLocality(Locality locality) {
        clearChoice();
        this.locality = asChild(locality);
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public boolean isSetPostOffice() {
        return postOffice != null;
    }

    public void setPostOffice(PostOffice postOffice) {
        clearChoice();
        this.postOffice = asChild(postOffice);
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public boolean isSetPostalCode() {
        return postalCode != null;
    }

    public void setPostalCode(PostalCode postalCode) {
        clearChoice();
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

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
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
        locality = null;
        postOffice = null;
        postalCode = null;
    }
}
