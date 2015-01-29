package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.Gateway;
import org.xmpp.packet.IQ;

public interface OutboundIQService {

	@Gateway
	void send(IQ iq);

}
