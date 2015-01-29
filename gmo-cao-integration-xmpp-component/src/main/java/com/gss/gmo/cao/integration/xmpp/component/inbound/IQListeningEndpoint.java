package com.gss.gmo.cao.integration.xmpp.component.inbound;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.xmpp.packet.IQ;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareEndpoint;
import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;
import com.gss.gmo.cao.integration.xmpp.component.core.IQListener;
import com.gss.gmo.cao.integration.xmpp.component.core.IQListener.IQType;

;

/**
 * @author linus_chien
 *
 */
public class IQListeningEndpoint extends AbstractComponentManagerAwareEndpoint {

	private IQType iqType;

	public IQListeningEndpoint(ComponentManager componentManager) {
		super(componentManager);
	}

	public void setIqType(IQType iqType) {
		this.iqType = iqType;
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:iq-inbound-channel-adapter";
	}

	@Override
	protected void doStart() {
		isTrue(this.initialized, this.getComponentName() + " [" + this.getComponentType() + "] must be initialized");
		notNull(iqType, "IQType must be set.");
		this.componentManager.addIQListener(iqType, new IQListener() {
			@Override
			public void handleIQ(IQ iq) {
				AbstractIntegrationMessageBuilder<IQ> messageBuilder = getMessageBuilderFactory().withPayload(iq);
				sendMessage(messageBuilder.build());
			}
		});
	}

}
