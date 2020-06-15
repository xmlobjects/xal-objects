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

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.XALObject;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

public class Longitude extends XALObject {
    private String degreesMeasure;
    private String minutesMeasure;
    private String secondsMeasure;
    private DirectionType direction;
    private Map<QName, String> otherAttributes;

    public String getDegreesMeasure() {
        return degreesMeasure;
    }

    public void setDegreesMeasure(String degreesMeasure) {
        this.degreesMeasure = degreesMeasure;
    }

    public String getMinutesMeasure() {
        return minutesMeasure;
    }

    public void setMinutesMeasure(String minutesMeasure) {
        this.minutesMeasure = minutesMeasure;
    }

    public String getSecondsMeasure() {
        return secondsMeasure;
    }

    public void setSecondsMeasure(String secondsMeasure) {
        this.secondsMeasure = secondsMeasure;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
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