package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.util.List;

public class PremiseNumberRangeTo extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private List<PremiseNumberPrefix> premiseNumberPrefixes;
    private List<PremiseNumber> premiseNumbers;
    private List<PremiseNumberSuffix> premiseNumberSuffixes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<PremiseNumberPrefix> getPremiseNumberPrefixes() {
        if (premiseNumberPrefixes == null)
            premiseNumberPrefixes = new ChildList<>(this);

        return premiseNumberPrefixes;
    }

    public void setPremiseNumberPrefixes(List<PremiseNumberPrefix> premiseNumberPrefixes) {
        this.premiseNumberPrefixes = asChild(premiseNumberPrefixes);
    }

    public List<PremiseNumber> getPremiseNumbers() {
        if (premiseNumbers == null)
            premiseNumbers = new ChildList<>(this);

        return premiseNumbers;
    }

    public void setPremiseNumbers(List<PremiseNumber> premiseNumbers) {
        this.premiseNumbers = asChild(premiseNumbers);
    }

    public List<PremiseNumberSuffix> getPremiseNumberSuffixes() {
        if (premiseNumberSuffixes == null)
            premiseNumberSuffixes = new ChildList<>(this);

        return premiseNumberSuffixes;
    }

    public void setPremiseNumberSuffixes(List<PremiseNumberSuffix> premiseNumberSuffixes) {
        this.premiseNumberSuffixes = asChild(premiseNumberSuffixes);
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
