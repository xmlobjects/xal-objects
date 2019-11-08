package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Thoroughfare extends XALObject {
    private List<AddressLine> addressLines;
    private List<ThoroughfareNumberOrRange> thoroughfareNumberOrRanges;
    private List<ThoroughfareNumberPrefix> thoroughfareNumberPrefixes;
    private List<ThoroughfareNumberSuffix> thoroughfareNumberSuffixes;
    private ThoroughfarePreDirection thoroughfarePreDirection;
    private ThoroughfareLeadingType thoroughfareLeadingType;
    private List<ThoroughfareName> thoroughfareNames;
    private ThoroughfareTrailingType thoroughfareTrailingType;
    private ThoroughfarePostDirection thoroughfarePostDirection;
    private DependentThoroughfare dependentThoroughfare;

    // Choice
    private DependentLocality dependentLocality;
    private Premise premise;
    private Firm firm;
    private PostalCode postalCode;

    private List<GenericElement> genericElements;
    private String type;
    private DependentThoroughfares dependentThoroughfares;
    private String dependentThoroughfaresIndicator;
    private String dependentThoroughfaresConnector;
    private String dependentThoroughfaresType;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<ThoroughfareNumberOrRange> getThoroughfareNumberOrRanges() {
        if (thoroughfareNumberOrRanges == null)
            thoroughfareNumberOrRanges = new ChildList<>(this);

        return thoroughfareNumberOrRanges;
    }

    public void setThoroughfareNumberOrRanges(List<ThoroughfareNumberOrRange> thoroughfareNumberOrRanges) {
        this.thoroughfareNumberOrRanges = asChild(thoroughfareNumberOrRanges);
    }

    public List<ThoroughfareNumberPrefix> getThoroughfareNumberPrefixes() {
        if (thoroughfareNumberPrefixes == null)
            thoroughfareNumberPrefixes = new ChildList<>(this);

        return thoroughfareNumberPrefixes;
    }

    public void setThoroughfareNumberPrefixes(List<ThoroughfareNumberPrefix> thoroughfareNumberPrefixes) {
        this.thoroughfareNumberPrefixes = asChild(thoroughfareNumberPrefixes);
    }

    public List<ThoroughfareNumberSuffix> getThoroughfareNumberSuffixes() {
        if (thoroughfareNumberSuffixes == null)
            thoroughfareNumberSuffixes = new ChildList<>(this);

        return thoroughfareNumberSuffixes;
    }

    public void setThoroughfareNumberSuffixes(List<ThoroughfareNumberSuffix> thoroughfareNumberSuffixes) {
        this.thoroughfareNumberSuffixes = asChild(thoroughfareNumberSuffixes);
    }

    public ThoroughfarePreDirection getThoroughfarePreDirection() {
        return thoroughfarePreDirection;
    }

    public void setThoroughfarePreDirection(ThoroughfarePreDirection thoroughfarePreDirection) {
        this.thoroughfarePreDirection = asChild(thoroughfarePreDirection);
    }

    public ThoroughfareLeadingType getThoroughfareLeadingType() {
        return thoroughfareLeadingType;
    }

    public void setThoroughfareLeadingType(ThoroughfareLeadingType thoroughfareLeadingType) {
        this.thoroughfareLeadingType = asChild(thoroughfareLeadingType);
    }

    public List<ThoroughfareName> getThoroughfareNames() {
        if (thoroughfareNames == null)
            thoroughfareNames = new ChildList<>(this);

        return thoroughfareNames;
    }

    public void setThoroughfareNames(List<ThoroughfareName> thoroughfareNames) {
        this.thoroughfareNames = asChild(thoroughfareNames);
    }

    public ThoroughfareTrailingType getThoroughfareTrailingType() {
        return thoroughfareTrailingType;
    }

    public void setThoroughfareTrailingType(ThoroughfareTrailingType thoroughfareTrailingType) {
        this.thoroughfareTrailingType = asChild(thoroughfareTrailingType);
    }

    public ThoroughfarePostDirection getThoroughfarePostDirection() {
        return thoroughfarePostDirection;
    }

    public void setThoroughfarePostDirection(ThoroughfarePostDirection thoroughfarePostDirection) {
        this.thoroughfarePostDirection = asChild(thoroughfarePostDirection);
    }

    public DependentThoroughfare getDependentThoroughfare() {
        return dependentThoroughfare;
    }

    public void setDependentThoroughfare(DependentThoroughfare dependentThoroughfare) {
        this.dependentThoroughfare = asChild(dependentThoroughfare);
    }

    public DependentLocality getDependentLocality() {
        return dependentLocality;
    }

    public boolean isSetDependentLocality() {
        return dependentLocality != null;
    }

    public void setDependentLocality(DependentLocality dependentLocality) {
        clearChoice();
        this.dependentLocality = asChild(dependentLocality);
    }

    public Premise getPremise() {
        return premise;
    }

    public boolean isSetPremise() {
        return premise != null;
    }

    public void setPremise(Premise premise) {
        clearChoice();
        this.premise = asChild(premise);
    }

    public Firm getFirm() {
        return firm;
    }

    public boolean isSetFirm() {
        return firm != null;
    }

    public void setFirm(Firm firm) {
        clearChoice();
        this.firm = asChild(firm);
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

    public DependentThoroughfares getDependentThoroughfares() {
        return dependentThoroughfares;
    }

    public void setDependentThoroughfares(DependentThoroughfares dependentThoroughfares) {
        this.dependentThoroughfares = dependentThoroughfares;
    }

    public String getDependentThoroughfaresIndicator() {
        return dependentThoroughfaresIndicator;
    }

    public void setDependentThoroughfaresIndicator(String dependentThoroughfaresIndicator) {
        this.dependentThoroughfaresIndicator = dependentThoroughfaresIndicator;
    }

    public String getDependentThoroughfaresConnector() {
        return dependentThoroughfaresConnector;
    }

    public void setDependentThoroughfaresConnector(String dependentThoroughfaresConnector) {
        this.dependentThoroughfaresConnector = dependentThoroughfaresConnector;
    }

    public String getDependentThoroughfaresType() {
        return dependentThoroughfaresType;
    }

    public void setDependentThoroughfaresType(String dependentThoroughfaresType) {
        this.dependentThoroughfaresType = dependentThoroughfaresType;
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
        dependentLocality = null;
        premise = null;
        firm = null;
        postalCode = null;
    }
}
