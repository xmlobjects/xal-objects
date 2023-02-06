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

import org.xmlobjects.model.Child;
import org.xmlobjects.model.ChildList;
import org.xmlobjects.util.copy.Copyable;

import java.util.List;

public abstract class XALObject implements Child, Copyable {
    private Child parent;

    protected final <T extends Child> T asChild(T child) {
        if (child != null)
            child.setParent(this);

        return child;
    }

    protected final <T extends Child> List<T> asChild(List<T> child) {
        if (child instanceof ChildList) {
            ((ChildList<T>) child).setParent(this);
            return child;
        } else
            return child != null ? new ChildList<>(child, this) : null;
    }

    @Override
    public final Child getParent() {
        return parent;
    }

    @Override
    public final void setParent(Child parent) {
        this.parent = parent;
    }
}
