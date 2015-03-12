package com.gss.gmo.cao.integration.xmpp.component.inbound;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.messaging.Message;
import org.xmpp.packet.IQ;

import com.gss.gmo.cao.integration.xmpp.component.core.AbstractComponentManagerAwareGatewaySupport;
import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;
import com.gss.gmo.cao.integration.xmpp.component.core.IQHandler;

/**
 * @author linus_chien
 *
 */
public class IQHandlerGateway extends AbstractComponentManagerAwareGatewaySupport {

	private IQHandler.IQType iqType;

	public IQHandlerGateway(ComponentManager componentManager) {
		super(componentManager);
	}

	public void setIqType(IQHandler.IQType iqType) {
		this.iqType = iqType;
	}

	@Override
	public String getComponentType() {
		return "xmpp-component:iq-inbound-channel-gateway";
	}

	@Override
	protected void onInit() throws Exception {
		super.onInit();
		isTrue(this.initialized, this.getComponentName() + " [" + this.getComponentType() + "] must be initialized");
		notNull(iqType, "IQType must be set.");
		this.componentManager.addIQHandler(iqType, new IQHandler() {
			@Override
			public IQ handleIQ(IQ iq) {
				AbstractIntegrationMessageBuilder<IQ> messageBuilder = getMessageBuilderFactory().withPayload(iq);
				Message<?> reply = sendAndReceiveMessage(messageBuilder.build());
				return (IQ) reply.getPayload();
			}
		});
	}

}
