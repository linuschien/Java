package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.IQ;

public interface IQListener {

	void handleIQ(IQ iq);

}
