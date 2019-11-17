package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public class SubPremiseLocation extends XALObject implements GrPostal {
    private String content;
    private String code;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
