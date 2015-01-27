package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.Presence;

public interface PresenceListener {

	void handlePresence(Presence presence);

}
