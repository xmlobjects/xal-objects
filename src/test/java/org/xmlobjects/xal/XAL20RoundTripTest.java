/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlobjects.XMLObjects;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLReaderFactory;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.stream.XMLWriterFactory;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.util.XALConstants;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class XAL20RoundTripTest {
    private static XMLObjects xmlObjects;

    @BeforeAll
    static void setup() throws Exception {
        xmlObjects = XMLObjects.newInstance();
    }

    private static Address read(String xml) throws Exception {
        try (XMLReader reader = XMLReaderFactory.newInstance(xmlObjects).createReader(new StringReader(xml))) {
            return xmlObjects.fromXML(reader, Address.class);
        }
    }

    private static String write(Address address) throws Exception {
        StringWriter out = new StringWriter();
        try (XMLWriter writer = XMLWriterFactory.newInstance(xmlObjects).createWriter(out)) {
            xmlObjects.toXML(writer, address, XALConstants.XAL_2_0_NAMESPACE);
        }

        return out.toString();
    }

    @Test
    void validFromAndValidToDate() throws Exception {
        Address address = read("<AddressDetails xmlns=\"" + XALConstants.XAL_2_0_NAMESPACE + "\""
                + " ValidFromDate=\"2020-01-01T00:00:00Z\" ValidToDate=\"2025-01-01T00:00:00Z\"/>");

        assertEquals(OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), address.getDateValidFrom());
        assertEquals(OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), address.getDateValidTo());

        String xml = write(address);
        assertTrue(xml.contains("ValidFromDate=\"2020-01-01T00:00:00Z\""), xml);
        assertTrue(xml.contains("ValidToDate=\"2025-01-01T00:00:00Z\""), xml);
    }

    @Test
    void typeOfDependentThoroughfare() throws Exception {
        Address address = read("<AddressDetails xmlns=\"" + XALConstants.XAL_2_0_NAMESPACE + "\"><Country><Locality>"
                + "<Thoroughfare Type=\"Road\"><ThoroughfareName>Main</ThoroughfareName>"
                + "<DependentThoroughfare Type=\"Street\"><ThoroughfareName>Side</ThoroughfareName></DependentThoroughfare>"
                + "</Thoroughfare></Locality></Country></AddressDetails>");

        String xml = write(address);
        assertTrue(xml.matches("(?s).*<([A-Za-z0-9]+:)?Thoroughfare Type=\"Road\".*"), xml);
        assertTrue(xml.matches("(?s).*<([A-Za-z0-9]+:)?DependentThoroughfare Type=\"Street\".*"), xml);
    }

    @Test
    void otherAttributesAreKept() throws Exception {
        Address address = read("<AddressDetails xmlns=\"" + XALConstants.XAL_2_0_NAMESPACE + "\""
                + " xmlns:x=\"urn:example\" x:a=\"1\" x:b=\"2\"/>");

        assertEquals("1", address.getOtherAttributes().getValue("urn:example", "a"));
        assertEquals("2", address.getOtherAttributes().getValue("urn:example", "b"));
    }
}
