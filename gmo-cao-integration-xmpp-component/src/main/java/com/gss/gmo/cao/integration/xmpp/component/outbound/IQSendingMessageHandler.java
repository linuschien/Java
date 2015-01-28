package com.gss.gmo.cao.integration.xmpp.component.outbound;

import static org.springframework.util.Assert.isTrue;

import org.springframework.messaging.Message;
import org.xmpp.packet.IQ;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareMessageHandler;
import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;

/**
 * @author linus_chien
 *
 */
public class IQSendingMessageHandler extends AbstractComponentManagerAwareMessageHandler {

	public IQSendingMessageHandler(ComponentManager componentManager) {
		super(componentManager);
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:iq-outbound-channel-adapter";
	}

	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {
		isTrue(this.initialized, this.getComponentName() + "#" + this.getComponentType() + " must be initialized");
		Object messageBody = message.getPayload();
		IQ iq = null;
		if (messageBody instanceof IQ) {
			iq = (IQ) messageBody;
		}
		this.componentManager.sendPacket(iq);
	}

}
