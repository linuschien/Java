package com.gss.gmo.cao.integration.xmpp.component.core;

import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;

/**
 * @author linus_chien
 *
 */
public class AbstractComponentManagerAwareMessageHandler extends AbstractMessageHandler {

	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {

	}

}
