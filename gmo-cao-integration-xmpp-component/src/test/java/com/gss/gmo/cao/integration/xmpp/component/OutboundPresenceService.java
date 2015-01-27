package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.Gateway;
import org.xmpp.packet.Presence;

public interface OutboundPresenceService {

	@Gateway
	void send(Presence presence);

}
