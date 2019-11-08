package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.xal.model.PostalServiceElements;

import javax.xml.namespace.QName;

public class PostalServiceElementsAdapter implements ObjectBuilder<PostalServiceElements>, ObjectSerializer<PostalServiceElements> {

    @Override
    public PostalServiceElements createObject(QName name) throws ObjectBuildException {
        return new PostalServiceElements();
    }
}
