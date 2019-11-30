package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public class DependentLocalityNumber extends XALObject implements AddressObject, GrPostal {
    private String content;
    private NameNumberOccurrence nameNumberOccurrence;
    private String code;
    private Map<QName, String> otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NameNumberOccurrence getNameNumberOccurrence() {
        return nameNumberOccurrence;
    }

    public void setNameNumberOccurrence(NameNumberOccurrence nameNumberOccurrence) {
        this.nameNumberOccurrence = nameNumberOccurrence;
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
