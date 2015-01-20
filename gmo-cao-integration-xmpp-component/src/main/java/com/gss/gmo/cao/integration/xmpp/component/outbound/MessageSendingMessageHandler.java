package com.gss.gmo.cao.integration.xmpp.component.outbound;

import static org.springframework.util.Assert.isTrue;

import org.springframework.messaging.Message;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareMessageHandler;
import com.gss.gmo.cao.integration.xmpp.component.core.SingleSubdomainComponentManager;

/**
 * @author linus_chien
 *
 */
public class MessageSendingMessageHandler extends AbstractComponentManagerAwareMessageHandler {

	public MessageSendingMessageHandler(SingleSubdomainComponentManager componentManager) {
		super(componentManager);
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:message-outbound-channel-adapter";
	}

	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {
		isTrue(this.initialized, this.getComponentName() + "#" + this.getComponentType() + " must be initialized");
		Object messageBody = message.getPayload();
		org.xmpp.packet.Message xmppMessage = null;
		if (messageBody instanceof org.xmpp.packet.Message) {
			xmppMessage = (org.xmpp.packet.Message) messageBody;
		}
		this.componentManager.sendPacket(xmppMessage);
	}

}
