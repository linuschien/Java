package com.gss.gmo.cao.integration.xmpp.component.inbound;

import static org.springframework.util.Assert.isTrue;

import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.xmpp.packet.Presence;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareEndpoint;
import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;
import com.gss.gmo.cao.integration.xmpp.component.core.PresenceListener;

/**
 * @author linus_chien
 *
 */
public class PresenceListeningEndpoint extends AbstractComponentManagerAwareEndpoint {

	public PresenceListeningEndpoint(ComponentManager componentManager) {
		super(componentManager);
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:presence-inbound-channel-adapter";
	}

	@Override
	protected void doStart() {
		isTrue(this.initialized, this.getComponentName() + " [" + this.getComponentType() + "] must be initialized");
		this.componentManager.setPresenceListener(new PresenceListener() {
			@Override
			public void handlePresence(Presence presence) {
				AbstractIntegrationMessageBuilder<Presence> messageBuilder = getMessageBuilderFactory().withPayload(presence);
				sendMessage(messageBuilder.build());
			}
		});
	}

}
