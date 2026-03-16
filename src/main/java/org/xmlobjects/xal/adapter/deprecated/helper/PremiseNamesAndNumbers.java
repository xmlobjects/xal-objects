/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.adapter.deprecated.helper;

import org.xmlobjects.xal.model.AbstractPremises;
import org.xmlobjects.xal.model.types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PremiseNamesAndNumbers {
    private PremisesName premiseLocation;
    private List<PremisesName> names;
    private List<Identifier> numbers;
    private NumberRange numberRange;
    private List<Identifier> prefixes;
    private List<Identifier> suffixes;
    private boolean isRange;

    public static PremiseNamesAndNumbers of(AbstractPremises premise) {
        PremiseNamesAndNumbers result = new PremiseNamesAndNumbers();
        if (premise.isSetNameElementOrNumber()) {
            ParsedNumber number = new ParsedNumber();
            for (PremisesNameOrNumber nameElementOrNumber : premise.getNameElementOrNumber()) {
                if (nameElementOrNumber.isSetNameElement()) {
                    PremisesName nameElement = nameElementOrNumber.getNameElement();
                    result.addName(nameElement);
                } else if (nameElementOrNumber.isSetNumber()) {
                    Identifier identifier = nameElementOrNumber.getNumber();
                    if (!number.accepts(identifier)) {
                        result.addNumber(number);
                        number = new ParsedNumber();
                    }

                    number.add(identifier);
                }
            }

            result.addNumber(number);
        }

        return result;
    }

    private void addName(PremisesName name) {
        if (name.getNameType() != PremisesNameType.LOCATION) {
            if (names == null)
                names = new ArrayList<>();

            names.add(name);
        } else if (premiseLocation == null)
            premiseLocation = name;
    }

    private void addNumber(ParsedNumber number) {
        if (number.getType() == IdentifierElementType.RANGE_FROM) {
            if (numbers == null)
                numbers = new ArrayList<>();

            numberRange = new NumberRange(number);
            isRange = true;
            return;
        }

        if (isRange) {
            if (number.getType() == IdentifierElementType.RANGE_TO) {
                numberRange.setRangeTo(number);
                isRange = false;
                return;
            } else if (number.getType() == IdentifierElementType.SEPARATOR) {
                numberRange.setSeparator(number.getNumbers().get(0));
                addPrefixes(number.getPrefixes());
                addSuffixes(number.getSuffixes());
                return;
            } else
                isRange = false;
        }

        addNumbers(number.getNumbers());
        addPrefixes(number.getPrefixes());
        addSuffixes(number.getSuffixes());
    }

    public PremisesName getPremiseLocation() {
        return premiseLocation;
    }

    public List<PremisesName> getNames() {
        return names != null ? names : Collections.emptyList();
    }

    public List<Identifier> getNumbers() {
        return numbers != null ? numbers : Collections.emptyList();
    }

    public NumberRange getNumberRange() {
        return numberRange;
    }

    private void addNumbers(List<Identifier> numbers) {
        if (!numbers.isEmpty()) {
            if (this.numbers == null)
                this.numbers = new ArrayList<>();

            this.numbers.addAll(numbers);
        }
    }

    public List<Identifier> getPrefixes() {
        return prefixes != null ? prefixes : Collections.emptyList();
    }

    private void addPrefixes(List<Identifier> prefixes) {
        if (!prefixes.isEmpty()) {
            if (this.prefixes == null)
                this.prefixes = new ArrayList<>();

            this.prefixes.addAll(prefixes);
        }
    }

    public List<Identifier> getSuffixes() {
        return suffixes != null ? suffixes : Collections.emptyList();
    }

    private void addSuffixes(List<Identifier> suffixes) {
        if (!suffixes.isEmpty()) {
            if (this.suffixes == null)
                this.suffixes = new ArrayList<>();

            this.suffixes.addAll(suffixes);
        }
    }
}
