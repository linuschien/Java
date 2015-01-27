package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.Packet;

/**
 * @author linus_chien
 *
 */
public interface ComponentManager {

	void sendPacket(Packet packet);

	void setMessageListener(MessageListener messageListener);

	void setPresenceListener(PresenceListener presenceListener);

}