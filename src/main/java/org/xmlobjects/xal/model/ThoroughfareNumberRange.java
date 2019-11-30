package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThoroughfareNumberRange extends XALObject implements AddressObject, GrPostal {
    private List<AddressLine> addressLines;
    private ThoroughfareNumberFrom thoroughfareNumberFrom;
    private ThoroughfareNumberTo thoroughfareNumberTo;
    private RangeType rangeType;
    private String indicator;
    private String separator;
    private IndicatorOccurrence indicatorOccurrence;
    private NumberRangeOccurrence numberRangeOccurrence;
    private String type;
    private String code;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public ThoroughfareNumberFrom getThoroughfareNumberFrom() {
        return thoroughfareNumberFrom;
    }

    public void setThoroughfareNumberFrom(ThoroughfareNumberFrom thoroughfareNumberFrom) {
        this.thoroughfareNumberFrom = asChild(thoroughfareNumberFrom);
    }

    public ThoroughfareNumberTo getThoroughfareNumberTo() {
        return thoroughfareNumberTo;
    }

    public void setThoroughfareNumberTo(ThoroughfareNumberTo thoroughfareNumberTo) {
        this.thoroughfareNumberTo = asChild(thoroughfareNumberTo);
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public IndicatorOccurrence getIndicatorOccurrence() {
        return indicatorOccurrence;
    }

    public void setIndicatorOccurrence(IndicatorOccurrence indicatorOccurrence) {
        this.indicatorOccurrence = indicatorOccurrence;
    }

    public NumberRangeOccurrence getNumberRangeOccurrence() {
        return numberRangeOccurrence;
    }

    public void setNumberRangeOccurrence(NumberRangeOccurrence numberRangeOccurrence) {
        this.numberRangeOccurrence = numberRangeOccurrence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
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
