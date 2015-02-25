package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.Packet;

import com.gss.gmo.cao.integration.xmpp.component.core.IQListener.IQType;

/**
 * @author linus_chien
 *
 */
public interface ComponentManager {

	void sendPacket(Packet packet);

	void setMessageListener(MessageListener messageListener);

	void setPresenceListener(PresenceListener presenceListener);

	void addIQListener(IQType iqType, IQListener iqListener);

	void addIQHandler(IQHandler.IQType iqType, IQHandler iqHandler);

}