package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependentLocality extends XALObject {
    private List<AddressLine> addressLines;
    private List<DependentLocalityName> dependentLocalityNames;
    private DependentLocalityNumber dependentLocalityNumber;
    private PostBox postBox;
    private LargeMailUser largeMailUser;
    private PostOffice postOffice;
    private PostalRoute postalRoute;
    private Thoroughfare thoroughfare;
    private Premise premise;
    private DependentLocality dependentLocality;
    private PostalCode postalCode;
    private List<GenericElement> genericElements;
    private String type;
    private String usageType;
    private String connector;
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

    public List<DependentLocalityName> getDependentLocalityNames() {
        if (dependentLocalityNames == null)
            dependentLocalityNames = new ChildList<>(this);

        return dependentLocalityNames;
    }

    public void setDependentLocalityNames(List<DependentLocalityName> dependentLocalityNames) {
        this.dependentLocalityNames = asChild(dependentLocalityNames);
    }

    public DependentLocalityNumber getDependentLocalityNumber() {
        return dependentLocalityNumber;
    }

    public void setDependentLocalityNumber(DependentLocalityNumber dependentLocalityNumber) {
        this.dependentLocalityNumber = asChild(dependentLocalityNumber);
    }

    public PostBox getPostBox() {
        return postBox;
    }

    public boolean isSetPostBox() {
        return postBox != null;
    }

    public void setPostBox(PostBox postBox) {
        clearChoice();
        this.postBox = asChild(postBox);
    }

    public LargeMailUser getLargeMailUser() {
        return largeMailUser;
    }

    public boolean isSetLargeMailUser() {
        return largeMailUser != null;
    }

    public void setLargeMailUser(LargeMailUser largeMailUser) {
        clearChoice();
        this.largeMailUser = asChild(largeMailUser);
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

    public PostalRoute getPostalRoute() {
        return postalRoute;
    }

    public boolean isSetPostalRoute() {
        return postalRoute != null;
    }

    public void setPostalRoute(PostalRoute postalRoute) {
        clearChoice();
        this.postalRoute = asChild(postalRoute);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        this.thoroughfare = asChild(thoroughfare);
    }

    public Premise getPremise() {
        return premise;
    }

    public void setPremise(Premise premise) {
        this.premise = asChild(premise);
    }

    public DependentLocality getDependentLocality() {
        return dependentLocality;
    }

    public void setDependentLocality(DependentLocality dependentLocality) {
        this.dependentLocality = asChild(dependentLocality);
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

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
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
        postBox = null;
        largeMailUser = null;
        postOffice = null;
        postalRoute = null;
    }
}
