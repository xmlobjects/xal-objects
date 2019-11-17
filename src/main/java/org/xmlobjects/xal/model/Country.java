package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Country extends XALObject {
    private List<AddressLine> addressLines;
    private List<CountryNameCode> countryNameCodes;
    private List<CountryName> countryNames;
    private AdministrativeArea administrativeArea;
    private Locality locality;
    private Thoroughfare thoroughfare;
    private List<GenericElement> genericElements;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<CountryNameCode> getCountryNameCodes() {
        if (countryNameCodes == null)
            countryNameCodes = new ChildList<>(this);

        return countryNameCodes;
    }

    public void setCountryNameCodes(List<CountryNameCode> countryNameCodes) {
        this.countryNameCodes = asChild(countryNameCodes);
    }

    public List<CountryName> getCountryNames() {
        if (countryNames == null)
            countryNames = new ChildList<>(this);

        return countryNames;
    }

    public void setCountryNames(List<CountryName> countryNames) {
        this.countryNames = asChild(countryNames);
    }

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public boolean isSetAdministrativeArea() {
        return administrativeArea != null;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        clearChoice();
        this.administrativeArea = asChild(administrativeArea);
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

    public Thoroughfare getThoroughfare() {
        return thoroughfare;
    }

    public boolean isSetThoroughfare() {
        return thoroughfare != null;
    }

    public void setThoroughfare(Thoroughfare thoroughfare) {
        clearChoice();
        this.thoroughfare = asChild(thoroughfare);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
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
        administrativeArea = null;
        locality = null;
        thoroughfare = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
