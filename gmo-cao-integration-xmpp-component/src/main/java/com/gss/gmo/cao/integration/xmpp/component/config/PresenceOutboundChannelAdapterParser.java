package com.gss.gmo.cao.integration.xmpp.component.config;

/**
 * @author linus_chien
 *
 */
public class PresenceOutboundChannelAdapterParser extends AbstractComponentOutboundChannelAdapterParser {

	@Override
	protected String getHandlerClassName() {
		return "com.gss.gmo.cao.integration.xmpp.component.outbound.PresenceSendingMessageHandler";
	}

}
