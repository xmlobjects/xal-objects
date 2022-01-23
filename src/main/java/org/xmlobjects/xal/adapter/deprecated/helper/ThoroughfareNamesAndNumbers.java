/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2021 Claus Nagel <claus.nagel@gmail.com>
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

package org.xmlobjects.xal.adapter.deprecated.helper;

import org.xmlobjects.xal.model.AbstractThoroughfare;
import org.xmlobjects.xal.model.types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThoroughfareNamesAndNumbers {
    private ThoroughfareName preDirection;
    private List<ThoroughfareName> names;
    private ThoroughfareName postDirection;
    private List<Object> numbers;
    private List<Identifier> prefixes;
    private List<Identifier> suffixes;
    private NumberRange numberRange;

    public static ThoroughfareNamesAndNumbers of(AbstractThoroughfare thoroughfare) {
        ThoroughfareNamesAndNumbers result = new ThoroughfareNamesAndNumbers();
        if (thoroughfare.isSetNameElementOrNumber()) {
            ParsedNumber number = new ParsedNumber();
            for (ThoroughfareNameOrNumber nameElementOrNumber : thoroughfare.getNameElementOrNumber()) {
                if (nameElementOrNumber.isSetNameElement()) {
                    ThoroughfareName nameElement = nameElementOrNumber.getNameElement();
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

    private void addName(ThoroughfareName nameElement) {
        if (nameElement.getNameType() == ThoroughfareNameType.PRE_DIRECTION && preDirection == null)
            preDirection = nameElement;
        else if (nameElement.getNameType() == ThoroughfareNameType.POST_DIRECTION && postDirection == null)
            postDirection = nameElement;
        else {
            if (names == null)
                names = new ArrayList<>();

            names.add(nameElement);
        }
    }

    private void addNumber(ParsedNumber number) {
        if (number.getType() == IdentifierElementType.RANGE_FROM) {
            if (numbers == null)
                numbers = new ArrayList<>();

            numberRange = new NumberRange(number);
            numbers.add(numberRange);
            return;
        }

        if (numberRange != null) {
            if (number.getType() == IdentifierElementType.RANGE_TO) {
                numberRange.setRangeTo(number);
                numberRange = null;
                return;
            } else if (number.getType() == IdentifierElementType.SEPARATOR) {
                numberRange.setSeparator(number.getNumbers().get(0));
                addPrefixes(number.getPrefixes());
                addSuffixes(number.getSuffixes());
                return;
            } else
                numberRange = null;
        }

        addNumbers(number.getNumbers());
        addPrefixes(number.getPrefixes());
        addSuffixes(number.getSuffixes());
    }

    public ThoroughfareName getPreDirection() {
        return preDirection;
    }

    public List<ThoroughfareName> getNames() {
        return names != null ? names : Collections.emptyList();
    }

    public ThoroughfareName getPostDirection() {
        return postDirection;
    }

    public List<Object> getNumbers() {
        return numbers != null ? numbers : Collections.emptyList();
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
