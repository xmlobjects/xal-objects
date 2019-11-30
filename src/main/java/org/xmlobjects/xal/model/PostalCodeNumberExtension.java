package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public class PostalCodeNumberExtension extends XALObject implements AddressObject, GrPostal {
    private String content;
    private String type;
    private String numberExtensionSeparator;
    private String code;
    private Map<QName, String> otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberExtensionSeparator() {
        return numberExtensionSeparator;
    }

    public void setNumberExtensionSeparator(String numberExtensionSeparator) {
        this.numberExtensionSeparator = numberExtensionSeparator;
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
