package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubPremise extends XALObject {
    private List<AddressLine> addressLines;
    private List<SubPremiseName> subPremiseNames;
    private SubPremiseLocation subPremiseLocation;
    private List<SubPremiseNumber> subPremiseNumbers;
    private List<SubPremiseNumberPrefix> subPremiseNumberPrefixes;
    private List<SubPremiseNumberSuffix> subPremiseNumberSuffixes;
    private List<BuildingName> buildingNames;
    private Firm firm;
    private MailStop mailStop;
    private PostalCode postalCode;
    private SubPremise subPremise;
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

    public List<SubPremiseName> getSubPremiseNames() {
        if (subPremiseNames == null)
            subPremiseNames = new ChildList<>(this);

        return subPremiseNames;
    }

    public void setSubPremiseNames(List<SubPremiseName> subPremiseNames) {
        this.subPremiseNames = asChild(subPremiseNames);
    }

    public SubPremiseLocation getSubPremiseLocation() {
        return subPremiseLocation;
    }

    public boolean isSetSubPremiseLocation() {
        return subPremiseLocation != null;
    }

    public void setSubPremiseLocation(SubPremiseLocation subPremiseLocation) {
        clearChoice();
        this.subPremiseLocation = asChild(subPremiseLocation);
    }

    public List<SubPremiseNumber> getSubPremiseNumbers() {
        return subPremiseNumbers;
    }

    public boolean isSetSubPremiseNumbers() {
        return subPremiseNumbers != null && !subPremiseNumbers.isEmpty();
    }

    public void setSubPremiseNumbers(List<SubPremiseNumber> subPremiseNumbers) {
        clearChoice();
        this.subPremiseNumbers = asChild(subPremiseNumbers);
    }

    public List<SubPremiseNumberPrefix> getSubPremiseNumberPrefixes() {
        if (subPremiseNumberPrefixes == null)
            subPremiseNumberPrefixes = new ChildList<>(this);

        return subPremiseNumberPrefixes;
    }

    public void setSubPremiseNumberPrefixes(List<SubPremiseNumberPrefix> subPremiseNumberPrefixes) {
        this.subPremiseNumberPrefixes = asChild(subPremiseNumberPrefixes);
    }

    public List<SubPremiseNumberSuffix> getSubPremiseNumberSuffixes() {
        if (subPremiseNumberSuffixes == null)
            subPremiseNumberSuffixes = new ChildList<>(this);

        return subPremiseNumberSuffixes;
    }

    public void setSubPremiseNumberSuffixes(List<SubPremiseNumberSuffix> subPremiseNumberSuffixes) {
        this.subPremiseNumberSuffixes = asChild(subPremiseNumberSuffixes);
    }

    public List<BuildingName> getBuildingNames() {
        if (buildingNames == null)
            buildingNames = new ChildList<>(this);

        return buildingNames;
    }

    public void setBuildingNames(List<BuildingName> buildingNames) {
        this.buildingNames = asChild(buildingNames);
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = asChild(firm);
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

    public SubPremise getSubPremise() {
        return subPremise;
    }

    public void setSubPremise(SubPremise subPremise) {
        this.subPremise = asChild(subPremise);
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
        subPremiseLocation = null;
        subPremiseNumbers = null;
    }
}
