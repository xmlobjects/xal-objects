package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Premise extends XALObject {
    private List<AddressLine> addressLines;
    private List<PremiseName> premiseNames;
    private PremiseLocation premiseLocation;
    private List<PremiseNumber> premiseNumbers;
    private PremiseNumberRange premiseNumberRange;
    private List<PremiseNumberPrefix> premiseNumberPrefixes;
    private List<PremiseNumberSuffix> premiseNumberSuffixes;
    private List<BuildingName> buildingNames;
    private List<SubPremise> subPremises;
    private Firm firm;
    private MailStop mailStop;
    private PostalCode postalCode;
    private Premise premise;
    private List<GenericElement> genericElements;
    private String type;
    private String premiseDependency;
    private String premiseDependencyType;
    private String premiseThoroughfareConnector;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<PremiseName> getPremiseNames() {
        if (premiseNames == null)
            premiseNames = new ChildList<>(this);

        return premiseNames;
    }

    public void setPremiseNames(List<PremiseName> premiseNames) {
        this.premiseNames = asChild(premiseNames);
    }

    public PremiseLocation getPremiseLocation() {
        return premiseLocation;
    }

    public boolean isSetPremiseLocation() {
        return premiseLocation != null;
    }

    public void setPremiseLocation(PremiseLocation premiseLocation) {
        clearPremiseLocationChoice();
        this.premiseLocation = asChild(premiseLocation);
    }

    public List<PremiseNumber> getPremiseNumbers() {
        if (premiseNumbers == null)
            premiseNumbers = new ChildList<>(this);

        return premiseNumbers;
    }

    public boolean isSetPremiseNumbers() {
        return premiseNumbers != null && !premiseNumbers.isEmpty();
    }

    public void setPremiseNumbers(List<PremiseNumber> premiseNumbers) {
        clearPremiseLocationChoice();
        this.premiseNumbers = asChild(premiseNumbers);
    }

    public PremiseNumberRange getPremiseNumberRange() {
        return premiseNumberRange;
    }

    public boolean isSetPremiseNumberRange() {
        return premiseNumberRange != null;
    }

    public void setPremiseNumberRange(PremiseNumberRange premiseNumberRange) {
        clearPremiseLocationChoice();
        this.premiseNumberRange = asChild(premiseNumberRange);
    }

    public List<PremiseNumberPrefix> getPremiseNumberPrefixes() {
        if (premiseNumberPrefixes == null)
            premiseNumberPrefixes = new ChildList<>(this);

        return premiseNumberPrefixes;
    }

    public void setPremiseNumberPrefixes(List<PremiseNumberPrefix> premiseNumberPrefixes) {
        this.premiseNumberPrefixes = asChild(premiseNumberPrefixes);
    }

    public List<PremiseNumberSuffix> getPremiseNumberSuffixes() {
        if (premiseNumberSuffixes == null)
            premiseNumberSuffixes = new ChildList<>(this);

        return premiseNumberSuffixes;
    }

    public void setPremiseNumberSuffixes(List<PremiseNumberSuffix> premiseNumberSuffixes) {
        this.premiseNumberSuffixes = asChild(premiseNumberSuffixes);
    }

    public List<BuildingName> getBuildingNames() {
        if (buildingNames == null)
            buildingNames = new ChildList<>(this);

        return buildingNames;
    }

    public void setBuildingNames(List<BuildingName> buildingNames) {
        this.buildingNames = asChild(buildingNames);
    }

    public List<SubPremise> getSubPremises() {
        if (subPremises == null)
            subPremises = new ChildList<>(this);

        return subPremises;
    }

    public boolean isSetSubPremises() {
        return subPremises != null && !subPremises.isEmpty();
    }

    public void setSubPremises(List<SubPremise> subPremises) {
        clearSubPremiseChoice();
        this.subPremises = asChild(subPremises);
    }

    public Firm getFirm() {
        return firm;
    }

    public boolean isSetFirm() {
        return firm != null;
    }

    public void setFirm(Firm firm) {
        clearSubPremiseChoice();
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

    public Premise getPremise() {
        return premise;
    }

    public void setPremise(Premise premise) {
        this.premise = asChild(premise);
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

    public String getPremiseDependency() {
        return premiseDependency;
    }

    public void setPremiseDependency(String premiseDependency) {
        this.premiseDependency = premiseDependency;
    }

    public String getPremiseDependencyType() {
        return premiseDependencyType;
    }

    public void setPremiseDependencyType(String premiseDependencyType) {
        this.premiseDependencyType = premiseDependencyType;
    }

    public String getPremiseThoroughfareConnector() {
        return premiseThoroughfareConnector;
    }

    public void setPremiseThoroughfareConnector(String premiseThoroughfareConnector) {
        this.premiseThoroughfareConnector = premiseThoroughfareConnector;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    private void clearPremiseLocationChoice() {
        premiseLocation = null;
        premiseNumbers = null;
        premiseNumberRange = null;
    }

    private void clearSubPremiseChoice() {
        subPremises = null;
        firm = null;
    }
}
