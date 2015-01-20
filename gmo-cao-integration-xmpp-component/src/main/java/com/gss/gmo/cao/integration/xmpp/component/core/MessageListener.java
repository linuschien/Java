package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.Message;

public interface MessageListener {

	void handleMessage(Message message);

}
