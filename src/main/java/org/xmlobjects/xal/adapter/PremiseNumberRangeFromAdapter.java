package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.PremiseNumber;
import org.xmlobjects.xal.model.PremiseNumberPrefix;
import org.xmlobjects.xal.model.PremiseNumberRangeFrom;
import org.xmlobjects.xal.model.PremiseNumberSuffix;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class PremiseNumberRangeFromAdapter implements ObjectBuilder<PremiseNumberRangeFrom>, ObjectSerializer<PremiseNumberRangeFrom> {

    @Override
    public PremiseNumberRangeFrom createObject(QName name) throws ObjectBuildException {
        return new PremiseNumberRangeFrom();
    }

    @Override
    public void buildChildObject(PremiseNumberRangeFrom object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "AddressLine":
                    object.getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    break;
                case "PremiseNumberPrefix":
                    object.getPremiseNumberPrefixes().add(reader.getObjectUsingBuilder(PremiseNumberPrefixAdapter.class));
                    break;
                case "PremiseNumber":
                    object.getPremiseNumbers().add(reader.getObjectUsingBuilder(PremiseNumberAdapter.class));
                    break;
                case "PremiseNumberSuffix":
                    object.getPremiseNumberSuffixes().add(reader.getObjectUsingBuilder(PremiseNumberSuffixAdapter.class));
                    break;
            }
        }
    }

    @Override
    public Element createElement(PremiseNumberRangeFrom object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberRangeFrom");
    }

    @Override
    public void writeChildElements(PremiseNumberRangeFrom object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        for (AddressLine addressLine : object.getAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLine"), addressLine, AddressLineAdapter.class, namespaces);

        for (PremiseNumberPrefix premiseNumberPrefix : object.getPremiseNumberPrefixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberPrefix"), premiseNumberPrefix, PremiseNumberPrefixAdapter.class, namespaces);

        for (PremiseNumber premiseNumber : object.getPremiseNumbers())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumber"), premiseNumber, PremiseNumberAdapter.class, namespaces);

        for (PremiseNumberSuffix premiseNumberSuffix : object.getPremiseNumberSuffixes())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PremiseNumberSuffix"), premiseNumberSuffix, PremiseNumberSuffixAdapter.class, namespaces);
    }
}
