package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThoroughfareNumberFrom extends XALObject implements AddressObject, GrPostal {
    private List<ThoroughfareNumberContent> contents;
    private String code;
    private Map<QName, String> otherAttributes;

    public List<ThoroughfareNumberContent> getContents() {
        if (contents == null)
            contents = new ChildList<>(this);

        return contents;
    }

    public void setContents(List<ThoroughfareNumberContent> contents) {
        this.contents = asChild(contents);
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
