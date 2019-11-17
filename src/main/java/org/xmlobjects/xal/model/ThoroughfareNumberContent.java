package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public class ThoroughfareNumberContent extends XALObject {
    private AddressLine addressLine;
    private ThoroughfareNumber thoroughfareNumber;
    private ThoroughfareNumberPrefix thoroughfareNumberPrefix;
    private ThoroughfareNumberSuffix thoroughfareNumberSuffix;
    private String string;

    public ThoroughfareNumberContent() {
    }

    public ThoroughfareNumberContent(AddressLine addressLine) {
        setAddressLine(addressLine);
    }

    public ThoroughfareNumberContent(ThoroughfareNumber thoroughfareNumber) {
        setThoroughfareNumber(thoroughfareNumber);
    }

    public ThoroughfareNumberContent(ThoroughfareNumberPrefix thoroughfareNumberPrefix) {
        setThoroughfareNumberPrefix(thoroughfareNumberPrefix);
    }

    public ThoroughfareNumberContent(ThoroughfareNumberSuffix thoroughfareNumberSuffix) {
        setThoroughfareNumberSuffix(thoroughfareNumberSuffix);
    }

    public ThoroughfareNumberContent(String string) {
        this.string = string;
    }

    public AddressLine getAddressLine() {
        return addressLine;
    }

    public boolean isSetAddressLine() {
        return addressLine != null;
    }

    public void setAddressLine(AddressLine addressLine) {
        clearChoice();
        this.addressLine = asChild(addressLine);
    }

    public ThoroughfareNumber getThoroughfareNumber() {
        return thoroughfareNumber;
    }

    public boolean isSetThoroughfareNumber() {
        return thoroughfareNumber != null;
    }

    public void setThoroughfareNumber(ThoroughfareNumber thoroughfareNumber) {
        clearChoice();
        this.thoroughfareNumber = asChild(thoroughfareNumber);
    }

    public ThoroughfareNumberPrefix getThoroughfareNumberPrefix() {
        return thoroughfareNumberPrefix;
    }

    public boolean isSetThoroughfareNumberPrefix() {
        return thoroughfareNumberPrefix != null;
    }

    public void setThoroughfareNumberPrefix(ThoroughfareNumberPrefix thoroughfareNumberPrefix) {
        clearChoice();
        this.thoroughfareNumberPrefix = asChild(thoroughfareNumberPrefix);
    }

    public ThoroughfareNumberSuffix getThoroughfareNumberSuffix() {
        return thoroughfareNumberSuffix;
    }

    public boolean isSetThoroughfareNumberSuffix() {
        return thoroughfareNumberSuffix != null;
    }

    public void setThoroughfareNumberSuffix(ThoroughfareNumberSuffix thoroughfareNumberSuffix) {
        clearChoice();
        this.thoroughfareNumberSuffix = asChild(thoroughfareNumberSuffix);
    }

    public String getString() {
        return string;
    }

    public boolean isSetString() {
        return string != null;
    }

    public void setString(String string) {
        clearChoice();
        this.string = string;
    }

    private void clearChoice() {
        addressLine = null;
        thoroughfareNumber = null;
        thoroughfareNumberPrefix = null;
        thoroughfareNumberSuffix = null;
        string = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
