/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2025 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.types.LatitudeAdapter;
import org.xmlobjects.xal.adapter.types.LongitudeAdapter;
import org.xmlobjects.xal.model.LocationByCoordinates;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

public class LocationByCoordinatesAdapter extends AddressObjectAdapter<LocationByCoordinates> {

    @Override
    public LocationByCoordinates createObject(QName name, Object parent) throws ObjectBuildException {
        return new LocationByCoordinates();
    }

    @Override
    public void initializeObject(LocationByCoordinates object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Meridian").normalize().ifPresent(object::setMeridian);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "MeridianCodeType").collapse().ifPresent(object::setMeridianCodeType);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Datum").normalize().ifPresent(object::setDatum);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "DatumCodeType").collapse().ifPresent(object::setDatumCodeType);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "Projection").normalize().ifPresent(object::setProjection);
        attributes.getValue(XALConstants.XAL_3_0_NAMESPACE, "ProjectionCodeType").collapse().ifPresent(object::setProjectionCodeType);
        XALBuilderHelper.buildDataQualityAttributes(object, attributes);
    }

    @Override
    public void buildChildObject(LocationByCoordinates object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_3_0_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "Latitude":
                    object.setLatitude(reader.getObjectUsingBuilder(LatitudeAdapter.class));
                    break;
                case "Longitude":
                    object.setLongitude(reader.getObjectUsingBuilder(LongitudeAdapter.class));
                    break;
            }
        }
    }

    @Override
    public void initializeElement(Element element, LocationByCoordinates object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.initializeElement(element, object, namespaces, writer);
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Meridian", TextContent.of(object.getMeridian()).normalize());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "MeridianCodeType", TextContent.of(object.getMeridianCodeType()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Datum", TextContent.of(object.getDatum()).normalize());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "DatumCodeType", TextContent.of(object.getDatumCodeType()).collapse());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "Projection", TextContent.of(object.getProjection()).normalize());
        element.addAttribute(XALConstants.XAL_3_0_NAMESPACE, "ProjectionCodeType", TextContent.of(object.getProjectionCodeType()).collapse());
        XALSerializerHelper.addDataQualityAttributes(element, object);
    }

    @Override
    public void writeChildElements(LocationByCoordinates object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        if (object.getLatitude() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Latitude"), object.getLatitude(), LatitudeAdapter.class, namespaces);

        if (object.getLongitude() != null)
            writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_3_0_NAMESPACE, "Longitude"), object.getLongitude(), LongitudeAdapter.class, namespaces);
    }
}
