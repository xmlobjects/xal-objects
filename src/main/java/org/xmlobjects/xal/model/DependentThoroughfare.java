package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependentThoroughfare extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private ThoroughfarePreDirection thoroughfarePreDirection;
    private ThoroughfareLeadingType thoroughfareLeadingType;
    private List<ThoroughfareName> thoroughfareNames;
    private ThoroughfareTrailingType thoroughfareTrailingType;
    private ThoroughfarePostDirection thoroughfarePostDirection;
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
