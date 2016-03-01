/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.server.management.client.container.status.card;

import java.util.Collections;
import javax.enterprise.event.Event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.server.api.model.Message;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.Severity;
import org.kie.server.controller.api.model.runtime.Container;
import org.kie.server.controller.api.model.runtime.ServerInstanceKey;
import org.kie.workbench.common.screens.server.management.client.events.ServerInstanceSelected;
import org.kie.workbench.common.screens.server.management.client.widget.card.CardPresenter;
import org.kie.workbench.common.screens.server.management.client.widget.card.body.BodyPresenter;
import org.kie.workbench.common.screens.server.management.client.widget.card.footer.FooterPresenter;
import org.kie.workbench.common.screens.server.management.client.widget.card.title.LinkTitlePresenter;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.mocks.EventSourceMock;
import org.uberfire.mvp.Command;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.eq;

@RunWith(MockitoJUnitRunner.class)
public class ContainerCardPresenterTest {

    @Mock
    ContainerCardPresenter.View view;

    @Mock
    Event<ServerInstanceSelected> remoteServerSelectedEvent = new EventSourceMock<ServerInstanceSelected>();

    ContainerCardPresenter presenter;

    @Before
    public void init() {
        presenter = spy( new ContainerCardPresenter( view, remoteServerSelectedEvent ) );
    }

    @Test
    public void testInit() {
        assertEquals( view, presenter.getView() );
    }

    @Test
    public void testDelete() {
        presenter.delete();

        verify( view ).delete();
    }

    @Test
    public void testSetup() {
        final LinkTitlePresenter linkTitlePresenter = spy( new LinkTitlePresenter( mock( LinkTitlePresenter.View.class ) ) );

        doReturn( linkTitlePresenter ).when( presenter ).newTitle();
        final BodyPresenter bodyPresenter = mock( BodyPresenter.class );
        doReturn( bodyPresenter ).when( presenter ).newBody();
        final FooterPresenter footerPresenter = mock( FooterPresenter.class );
        doReturn( footerPresenter ).when( presenter ).newFooter();
        final CardPresenter.View cardPresenterView = mock( CardPresenter.View.class );
        final CardPresenter cardPresenter = spy( new CardPresenter( cardPresenterView ) );
        doReturn( cardPresenter ).when( presenter ).newCard();

        final ServerInstanceKey serverInstanceKey = new ServerInstanceKey( "templateId", "serverName", "serverInstanceId", "url" );
        final Message message = new Message( Severity.INFO, "testMessage" );
        final ReleaseId resolvedReleasedId = new ReleaseId( "org.kie", "container", "1.0.0" );
        final Container container = new Container( "containerSpecId", "containerName", serverInstanceKey, Collections.singletonList( message ), resolvedReleasedId, null );

        presenter.setup( serverInstanceKey, container );

        verify( linkTitlePresenter ).setup( eq( serverInstanceKey.getServerName() ), any( Command.class ) );
        verify( bodyPresenter ).setup( message );
        verify( footerPresenter ).setup( container.getUrl(), resolvedReleasedId.getVersion() );
        verify( cardPresenter ).addTitle( linkTitlePresenter );
        verify( cardPresenter ).addBody( bodyPresenter );
        verify( cardPresenter ).addFooter( footerPresenter );
        verify( view ).setCard( cardPresenterView );

        linkTitlePresenter.onSelect();

        verify( remoteServerSelectedEvent ).fire( eq( new ServerInstanceSelected( serverInstanceKey ) ) );
    }
}