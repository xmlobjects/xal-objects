package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public class PostBoxNumberExtension extends XALObject implements AddressObject {
    private String content;
    private String numberExtensionSeparator;
    private Map<QName, String> otherAttributes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNumberExtensionSeparator() {
        return numberExtensionSeparator;
    }

    public void setNumberExtensionSeparator(String numberExtensionSeparator) {
        this.numberExtensionSeparator = numberExtensionSeparator;
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
