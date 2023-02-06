/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2023 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.model;

import org.xmlobjects.xal.model.types.DataQuality;
import org.xmlobjects.xal.model.types.DataQualityType;
import org.xmlobjects.xal.model.types.Latitude;
import org.xmlobjects.xal.model.types.Longitude;
import org.xmlobjects.xal.visitor.XALVisitor;

import java.time.OffsetDateTime;

public class LocationByCoordinates extends AddressObject implements DataQuality {
    private Latitude latitude;
    private Longitude longitude;
    private String meridian;
    private String meridianCodeType;
    private String datum;
    private String datumCodeType;
    private String projection;
    private String projectionCodeType;
    private DataQualityType dataQualityType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;

    public Latitude getLatitude() {
        return latitude;
    }

    public void setLatitude(Latitude latitude) {
        this.latitude = asChild(latitude);
    }

    public Longitude getLongitude() {
        return longitude;
    }

    public void setLongitude(Longitude longitude) {
        this.longitude = asChild(longitude);
    }

    public String getMeridian() {
        return meridian;
    }

    public void setMeridian(String meridian) {
        this.meridian = meridian;
    }

    public String getMeridianCodeType() {
        return meridianCodeType;
    }

    public void setMeridianCodeType(String meridianCodeType) {
        this.meridianCodeType = meridianCodeType;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getDatumCodeType() {
        return datumCodeType;
    }

    public void setDatumCodeType(String datumCodeType) {
        this.datumCodeType = datumCodeType;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getProjectionCodeType() {
        return projectionCodeType;
    }

    public void setProjectionCodeType(String projectionCodeType) {
        this.projectionCodeType = projectionCodeType;
    }

    @Override
    public DataQualityType getDataQualityType() {
        return dataQualityType;
    }

    @Override
    public void setDataQualityType(DataQualityType dataQualityType) {
        this.dataQualityType = dataQualityType;
    }

    @Override
    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public OffsetDateTime getValidTo() {
        return validTo;
    }

    @Override
    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
