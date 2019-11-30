package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public class SortingCode extends XALObject implements AddressObject, GrPostal {
    private String content;
    private String type;
    private String code;

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

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
