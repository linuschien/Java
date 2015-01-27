package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.ServiceActivator;
import org.xmpp.packet.Presence;

public class InboundPresenceService {

	private ComponentTest componentTest;

	void setComponentTest(ComponentTest componentTest) {
		this.componentTest = componentTest;
	}

	@ServiceActivator
	public void handlePresence(Presence presence) {
		componentTest.setStatus(presence.getStatus());
	}

}
