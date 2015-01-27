package com.gss.gmo.cao.integration.xmpp.component.outbound;

import static org.springframework.util.Assert.isTrue;

import org.springframework.messaging.Message;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareMessageHandler;
import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;

/**
 * @author linus_chien
 *
 */
public class PresenceSendingMessageHandler extends AbstractComponentManagerAwareMessageHandler {

	public PresenceSendingMessageHandler(ComponentManager componentManager) {
		super(componentManager);
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:presence-outbound-channel-adapter";
	}

	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {
		isTrue(this.initialized, this.getComponentName() + "#" + this.getComponentType() + " must be initialized");
		Object messageBody = message.getPayload();
		org.xmpp.packet.Presence xmppPresence = null;
		if (messageBody instanceof org.xmpp.packet.Presence) {
			xmppPresence = (org.xmpp.packet.Presence) messageBody;
		}
		this.componentManager.sendPacket(xmppPresence);
	}

}
