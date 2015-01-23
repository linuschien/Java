package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.Gateway;
import org.xmpp.packet.Message;

public interface OutboundMessageService {

	@Gateway
	void send(Message message);

}
