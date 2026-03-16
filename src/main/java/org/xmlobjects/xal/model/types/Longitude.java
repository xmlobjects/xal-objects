/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Claus Nagel <claus.nagel@gmail.com>
 */

package org.xmlobjects.xal.model.types;

import org.xmlobjects.xal.model.GenericAttributes;
import org.xmlobjects.xal.model.XALObject;

public class Longitude extends XALObject {
    private String degreesMeasure;
    private String minutesMeasure;
    private String secondsMeasure;
    private DirectionType direction;
    private GenericAttributes otherAttributes;

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

    public GenericAttributes getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new GenericAttributes();

        return otherAttributes;
    }

    public boolean isSetOtherAttributes() {
        return otherAttributes != null && !otherAttributes.isEmpty();
    }

    public void setOtherAttributes(GenericAttributes otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}
