package com.gss.gmo.cao.integration.xmpp.component.config;

/**
 * @author linus_chien
 *
 */
public class MessageInboundChannelAdapterParser extends AbstractComponentInboundChannelAdapterParser {

	@Override
	protected String getEndpointClassName() {
		return "com.gss.gmo.cao.integration.xmpp.component.inbound.MessageListeningEndpoint";
	}

}
