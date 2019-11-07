package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XAL extends XALObject {
    private List<AddressDetails> addressDetails;
    private List<GenericElement> genericElements;
    private String version;
    private Map<QName, String> otherAttributes;

    public List<AddressDetails> getAddressDetails() {
        if (addressDetails == null)
            addressDetails = new ChildList<>(this);

        return addressDetails;
    }

    public void setAddressDetails(List<AddressDetails> addressDetails) {
        this.addressDetails = asChild(addressDetails);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
