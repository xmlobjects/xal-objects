package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargeMailUser extends XALObject {
    private List<AddressLine> addressLines;
    private List<LargeMailUserName> largeMailUserNames;
    private LargeMailUserIdentifier largeMailUserIdentifier;
    private List<BuildingName> buildingNames;
    private Department department;
    private PostBox postBox;
    private Thoroughfare thoroughfare;
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

    public List<LargeMailUserName> getLargeMailUserNames() {
        if (largeMailUserNames == null)
            largeMailUserNames = new ChildList<>(this);

        return largeMailUserNames;
    }

    public void setLargeMailUserNames(List<LargeMailUserName> largeMailUserNames) {
        this.largeMailUserNames = asChild(largeMailUserNames);
    }

    public LargeMailUserIdentifier getLargeMailUserIdentifier() {
        return largeMailUserIdentifier;
    }

    public void setLargeMailUserIdentifier(LargeMailUserIdentifier largeMailUserIdentifier) {
        this.largeMailUserIdentifier = asChild(largeMailUserIdentifier);
    }

    public List<BuildingName> getBuildingNames() {
        if (buildingNames == null)
            buildingNames = new ChildList<>(this);

        return buildingNames;
    }

    public void setBuildingNames(List<BuildingName> buildingNames) {
        this.buildingNames = asChild(buildingNames);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = asChild(department);
    }

    public PostBox getPostBox() {
        return postBox;
    }

    public void setPostBox(PostBox postBox) {
        this.postBox = asChild(postBox);
    }

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        this.thoroughfare = asChild(thoroughfare);
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
}
