package org.xmlobjects.xal.adapter;

import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.AddressDetails;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "AddressDetails", namespaceURI = XALConstants.XAL_2_0_NAMESPACE)
public class AddressDetailsAdapter implements ObjectBuilder<AddressDetails>, ObjectSerializer<AddressDetails> {

    @Override
    public AddressDetails createObject(QName name) throws ObjectBuildException {
        return new AddressDetails();
    }

    @Override
    public void initializeObject(AddressDetails object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        attributes.getValue("AddressType").ifPresent(object::setAddressType);
        attributes.getValue("CurrentStatus").ifPresent(object::setCurrentStatus);
        attributes.getValue("ValidFromDate").ifPresent(object::setValidFromDate);
        attributes.getValue("ValidToDate").ifPresent(object::setValidToDate);
        attributes.getValue("Usage").ifPresent(object::setUsage);
        attributes.getValue("Code").ifPresent(object::setCode);
        attributes.getValue("AddressDetailsKey").ifPresent(object::setAddressDetailsKey);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public void buildChildObject(AddressDetails object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "PostalServiceElements":
                    object.setPostalServiceElements(reader.getObjectUsingBuilder(PostalServiceElementsAdapter.class));
                    break;
                case "Address":
                    object.setAddress(reader.getObjectUsingBuilder(AddressAdapter.class));
                    break;
                case "AddressLines":
                    object.setAddressLines(reader.getObjectUsingBuilder(AddressLinesAdapter.class));
                    break;
                case "Country":
                    object.setCountry(reader.getObjectUsingBuilder(CountryAdapter.class));
                    break;
                case "AdministrativeArea":
                    object.setAdministrativeArea(reader.getObjectUsingBuilder(AdministrativeAreaAdapter.class));
                    break;
                case "Locality":
                    object.setLocality(reader.getObjectUsingBuilder(LocalityAdapter.class));
                    break;
                case "Thoroughfare":
                    object.setThoroughfare(reader.getObjectUsingBuilder(ThoroughfareAdapter.class));
                    break;
            }
        } else
            XALBuilderHelper.buildGenericElements(object.getGenericElements(), reader);
    }

    @Override
    public Element createElement(AddressDetails object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressDetails");
    }

    @Override
    public void initializeElement(Element element, AddressDetails object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addAttribute("AddressType", object.getAddressType());
        element.addAttribute("CurrentStatus", object.getCurrentStatus());
        element.addAttribute("ValidFromDate", object.getValidFromDate());
        element.addAttribute("ValidToDate", object.getValidToDate());
        element.addAttribute("Usage", object.getUsage());
        element.addAttribute("Code", object.getCode());
        element.addAttribute("AddressDetailsKey", object.getAddressDetailsKey());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }

    @Override
    public void writeChildElements(AddressDetails object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.getPostalServiceElements() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "PostalServiceElements"), object.getPostalServiceElements(), PostalServiceElementsAdapter.class, namespaces);

        if (object.isSetAddress())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Address"), object.getAddress(), AddressAdapter.class, namespaces);
        else if (object.isSetAddressLines())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AddressLines"), object.getAddressLines(), AddressLinesAdapter.class, namespaces);
        else if (object.isSetCountry())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Country"), object.getCountry(), CountryAdapter.class, namespaces);
        else if (object.isSetAdministrativeArea())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "AdministrativeArea"), object.getAdministrativeArea(), AdministrativeAreaAdapter.class, namespaces);
        else if (object.isSetLocality())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Locality"), object.getLocality(), LocalityAdapter.class, namespaces);
        else if (object.isSetThoroughfare())
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "Thoroughfare"), object.getThoroughfare(), ThoroughfareAdapter.class, namespaces);

        XALSerializerHelper.serializeGenericElements(object.getGenericElements(), writer);
    }
}
