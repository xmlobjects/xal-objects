/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
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

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Premise extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private List<PremiseName> premiseNames;
    private PremiseLocation premiseLocation;
    private List<PremiseNumber> premiseNumbers;
    private PremiseNumberRange premiseNumberRange;
    private List<PremiseNumberPrefix> premiseNumberPrefixes;
    private List<PremiseNumberSuffix> premiseNumberSuffixes;
    private List<BuildingName> buildingNames;
    private List<SubPremise> subPremises;
    private Firm firm;
    private MailStop mailStop;
    private PostalCode postalCode;
    private Premise premise;
    private List<GenericElement> genericElements;
    private String type;
    private String premiseDependency;
    private String premiseDependencyType;
    private String premiseThoroughfareConnector;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<PremiseName> getPremiseNames() {
        if (premiseNames == null)
            premiseNames = new ChildList<>(this);

        return premiseNames;
    }

    public void setPremiseNames(List<PremiseName> premiseNames) {
        this.premiseNames = asChild(premiseNames);
    }

    public PremiseLocation getPremiseLocation() {
        return premiseLocation;
    }

    public boolean isSetPremiseLocation() {
        return premiseLocation != null;
    }

    public void setPremiseLocation(PremiseLocation premiseLocation) {
        clearPremiseLocationChoice();
        this.premiseLocation = asChild(premiseLocation);
    }

    public List<PremiseNumber> getPremiseNumbers() {
        if (premiseNumbers == null)
            premiseNumbers = new ChildList<>(this);

        return premiseNumbers;
    }

    public boolean isSetPremiseNumbers() {
        return premiseNumbers != null && !premiseNumbers.isEmpty();
    }

    public void setPremiseNumbers(List<PremiseNumber> premiseNumbers) {
        clearPremiseLocationChoice();
        this.premiseNumbers = asChild(premiseNumbers);
    }

    public PremiseNumberRange getPremiseNumberRange() {
        return premiseNumberRange;
    }

    public boolean isSetPremiseNumberRange() {
        return premiseNumberRange != null;
    }

    public void setPremiseNumberRange(PremiseNumberRange premiseNumberRange) {
        clearPremiseLocationChoice();
        this.premiseNumberRange = asChild(premiseNumberRange);
    }

    public List<PremiseNumberPrefix> getPremiseNumberPrefixes() {
        if (premiseNumberPrefixes == null)
            premiseNumberPrefixes = new ChildList<>(this);

        return premiseNumberPrefixes;
    }

    public void setPremiseNumberPrefixes(List<PremiseNumberPrefix> premiseNumberPrefixes) {
        this.premiseNumberPrefixes = asChild(premiseNumberPrefixes);
    }

    public List<PremiseNumberSuffix> getPremiseNumberSuffixes() {
        if (premiseNumberSuffixes == null)
            premiseNumberSuffixes = new ChildList<>(this);

        return premiseNumberSuffixes;
    }

    public void setPremiseNumberSuffixes(List<PremiseNumberSuffix> premiseNumberSuffixes) {
        this.premiseNumberSuffixes = asChild(premiseNumberSuffixes);
    }

    public List<BuildingName> getBuildingNames() {
        if (buildingNames == null)
            buildingNames = new ChildList<>(this);

        return buildingNames;
    }

    public void setBuildingNames(List<BuildingName> buildingNames) {
        this.buildingNames = asChild(buildingNames);
    }

    public List<SubPremise> getSubPremises() {
        if (subPremises == null)
            subPremises = new ChildList<>(this);

        return subPremises;
    }

    public boolean isSetSubPremises() {
        return subPremises != null && !subPremises.isEmpty();
    }

    public void setSubPremises(List<SubPremise> subPremises) {
        clearSubPremiseChoice();
        this.subPremises = asChild(subPremises);
    }

    public Firm getFirm() {
        return firm;
    }

    public boolean isSetFirm() {
        return firm != null;
    }

    public void setFirm(Firm firm) {
        clearSubPremiseChoice();
        this.firm = asChild(firm);
    }

    public MailStop getMailStop() {
        return mailStop;
    }

    public void setMailStop(MailStop mailStop) {
        this.mailStop = asChild(mailStop);
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = asChild(postalCode);
    }

    public Premise getPremise() {
        return premise;
    }

    public void setPremise(Premise premise) {
        this.premise = asChild(premise);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPremiseDependency() {
        return premiseDependency;
    }

    public void setPremiseDependency(String premiseDependency) {
        this.premiseDependency = premiseDependency;
    }

    public String getPremiseDependencyType() {
        return premiseDependencyType;
    }

    public void setPremiseDependencyType(String premiseDependencyType) {
        this.premiseDependencyType = premiseDependencyType;
    }

    public String getPremiseThoroughfareConnector() {
        return premiseThoroughfareConnector;
    }

    public void setPremiseThoroughfareConnector(String premiseThoroughfareConnector) {
        this.premiseThoroughfareConnector = premiseThoroughfareConnector;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    private void clearPremiseLocationChoice() {
        premiseLocation = null;
        premiseNumbers = null;
        premiseNumberRange = null;
    }

    private void clearSubPremiseChoice() {
        subPremises = null;
        firm = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
