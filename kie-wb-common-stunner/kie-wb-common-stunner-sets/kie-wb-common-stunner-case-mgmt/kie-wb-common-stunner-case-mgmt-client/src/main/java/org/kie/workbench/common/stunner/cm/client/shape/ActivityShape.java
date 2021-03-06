/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.cm.client.shape;

import org.kie.workbench.common.stunner.bpmn.definition.BPMNViewDefinition;
import org.kie.workbench.common.stunner.cm.client.shape.def.CaseManagementActivityShapeDef;
import org.kie.workbench.common.stunner.cm.client.shape.view.ActivityView;
import org.kie.workbench.common.stunner.shapes.client.PictureShape;
import org.kie.workbench.common.stunner.shapes.client.view.PictureShapeView;

public class ActivityShape
        extends AbstractCMContainerShape<BPMNViewDefinition, CaseManagementActivityShapeDef<BPMNViewDefinition, ActivityView>, ActivityView> {

    private final PictureShape iconShape;

    public ActivityShape(final CaseManagementActivityShapeDef shapeDef,
                         final PictureShapeView iconView,
                         final ActivityView view) {
        super(shapeDef,
              view);
        this.iconShape = new PictureShape(iconView);
        addChild(iconShape, Layout.TOP);
    }
}
