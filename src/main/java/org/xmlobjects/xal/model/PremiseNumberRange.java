package org.xmlobjects.xal.model;

public class PremiseNumberRange extends XALObject {
    private PremiseNumberRangeFrom premiseNumberRangeFrom;
    private PremiseNumberRangeTo premiseNumberRangeTo;
    private String rangeType;
    private String indicator;
    private String separator;
    private String type;
    private IndicatorOccurrence indicatorOccurrence;
    private NumberRangeOccurrence numberRangeOccurrence;

    public PremiseNumberRangeFrom getPremiseNumberRangeFrom() {
        return premiseNumberRangeFrom;
    }

    public void setPremiseNumberRangeFrom(PremiseNumberRangeFrom premiseNumberRangeFrom) {
        this.premiseNumberRangeFrom = asChild(premiseNumberRangeFrom);
    }

    public PremiseNumberRangeTo getPremiseNumberRangeTo() {
        return premiseNumberRangeTo;
    }

    public void setPremiseNumberRangeTo(PremiseNumberRangeTo premiseNumberRangeTo) {
        this.premiseNumberRangeTo = asChild(premiseNumberRangeTo);
    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
