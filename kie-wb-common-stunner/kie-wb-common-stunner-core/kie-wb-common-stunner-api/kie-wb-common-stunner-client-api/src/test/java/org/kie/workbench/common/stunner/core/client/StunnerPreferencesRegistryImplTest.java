/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.core.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.stunner.core.client.preferences.impl.StunnerPreferencesRegistryImpl;
import org.kie.workbench.common.stunner.core.preferences.StunnerPreferences;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class StunnerPreferencesRegistryImplTest {

    private StunnerPreferencesRegistryImpl preferencesRegistry;

    @Before
    public void setUp() {
        preferencesRegistry = new StunnerPreferencesRegistryImpl();
    }

    @Test
    public void testRegister() {
        StunnerPreferences stunnerPreferences = mock(StunnerPreferences.class);
        assertNull(preferencesRegistry.get());
        preferencesRegistry.register(stunnerPreferences);
        assertEquals(stunnerPreferences,
                     preferencesRegistry.get());
    }
}