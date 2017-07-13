/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.dmn.api.definition.v1_1;

import org.kie.workbench.common.dmn.api.property.dmn.Description;
import org.kie.workbench.common.dmn.api.property.dmn.Id;
import org.kie.workbench.common.dmn.api.property.dmn.Label;
import org.kie.workbench.common.dmn.api.property.dmn.Name;

public abstract class BusinessContextElement extends NamedElement {

    private String uri;

    public BusinessContextElement() {
    }

    public BusinessContextElement(final Id id,
                                  final Label label,
                                  final Description description,
                                  final Name name,
                                  final String uri) {
        super(id,
              label,
              description,
              name);
        this.uri = uri;
    }

    public String getURI() {
        return uri;
    }

    public void setURI(final String value) {
        this.uri = value;
    }
}