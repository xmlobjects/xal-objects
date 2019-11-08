package org.xmlobjects.xal.model;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public class SubPremiseNumber extends XALObject implements GrPostal {
    private String content;
    private String indicator;
    private IndicatorOccurrence indicatorOccurrence;
    private NumberTypeOccurrence numberTypeOccurrence;
    private String premiseNumberSeparator;
    private String type;
    private String code;
    private Map<QName, String> otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public IndicatorOccurrence getIndicatorOccurrence() {
        return indicatorOccurrence;
    }

    public void setIndicatorOccurrence(IndicatorOccurrence indicatorOccurrence) {
        this.indicatorOccurrence = indicatorOccurrence;
    }

    public NumberTypeOccurrence getNumberTypeOccurrence() {
        return numberTypeOccurrence;
    }

    public void setNumberTypeOccurrence(NumberTypeOccurrence numberTypeOccurrence) {
        this.numberTypeOccurrence = numberTypeOccurrence;
    }

    public String getPremiseNumberSeparator() {
        return premiseNumberSeparator;
    }

    public void setPremiseNumberSeparator(String premiseNumberSeparator) {
        this.premiseNumberSeparator = premiseNumberSeparator;
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
}
