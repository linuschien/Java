package com.gss.gmo.cao.integration.xmpp.component.inbound;

import static org.springframework.util.Assert.isTrue;

import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.xmpp.packet.Message;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareEndpoint;
import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;
import com.gss.gmo.cao.integration.xmpp.component.core.MessageListener;

/**
 * @author linus_chien
 *
 */
public class MessageListeningEndpoint extends AbstractComponentManagerAwareEndpoint {

	public MessageListeningEndpoint(ComponentManager componentManager) {
		super(componentManager);
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:message-inbound-channel-adapter";
	}

	@Override
	protected void doStart() {
		isTrue(this.initialized, this.getComponentName() + " [" + this.getComponentType() + "] must be initialized");
		this.componentManager.setMessageListener(new MessageListener() {
			@Override
			public void handleMessage(Message message) {
				AbstractIntegrationMessageBuilder<Message> messageBuilder = getMessageBuilderFactory().withPayload(message);
				sendMessage(messageBuilder.build());
			}
		});
	}

}
