package org.xmlobjects.xal.model;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public class PostBoxNumberPrefix extends XALObject implements GrPostal {
    private String content;
    private String numberPrefixSeparator;
    private String code;
    private Map<QName, String> otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNumberPrefixSeparator() {
        return numberPrefixSeparator;
    }

    public void setNumberPrefixSeparator(String numberPrefixSeparator) {
        this.numberPrefixSeparator = numberPrefixSeparator;
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
