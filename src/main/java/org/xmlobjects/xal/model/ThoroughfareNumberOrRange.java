package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public class ThoroughfareNumberOrRange extends XALObject {
    private ThoroughfareNumber thoroughfareNumber;
    private ThoroughfareNumberRange thoroughfareNumberRange;

    public ThoroughfareNumberOrRange() {
    }

    public ThoroughfareNumberOrRange(ThoroughfareNumber thoroughfareNumber) {
        setThoroughfareNumber(thoroughfareNumber);
    }

    public ThoroughfareNumberOrRange(ThoroughfareNumberRange thoroughfareNumberRange) {
        setThoroughfareNumberRange(thoroughfareNumberRange);
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

    public ThoroughfareNumberRange getThoroughfareNumberRange() {
        return thoroughfareNumberRange;
    }

    public boolean isSetThoroughfareNumberRange() {
        return thoroughfareNumberRange != null;
    }

    public void setThoroughfareNumberRange(ThoroughfareNumberRange thoroughfareNumberRange) {
        clearChoice();
        this.thoroughfareNumberRange = asChild(thoroughfareNumberRange);
    }

    private void clearChoice() {
        thoroughfareNumber = null;
        thoroughfareNumberRange = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
