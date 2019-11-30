package org.xmlobjects.xal.model;

import org.xmlobjects.xal.visitor.XALVisitor;

public interface AddressObject {
    void accept(XALVisitor visitor);
}
