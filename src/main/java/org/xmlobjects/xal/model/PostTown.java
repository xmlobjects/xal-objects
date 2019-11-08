package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostTown extends XALObject {
    private List<AddressLine> addressLines;
    private List<PostTownName> postTownNames;
    private PostTownSuffix postTownSuffix;
    private String type;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<PostTownName> getPostTownNames() {
        if (postTownNames == null)
            postTownNames = new ChildList<>(this);

        return postTownNames;
    }

    public void setPostTownNames(List<PostTownName> postTownNames) {
        this.postTownNames = asChild(postTownNames);
    }

    public PostTownSuffix getPostTownSuffix() {
        return postTownSuffix;
    }

    public void setPostTownSuffix(PostTownSuffix postTownSuffix) {
        this.postTownSuffix = asChild(postTownSuffix);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
