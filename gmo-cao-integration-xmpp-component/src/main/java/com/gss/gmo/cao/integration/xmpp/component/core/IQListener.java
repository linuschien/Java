package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.IQ;

public interface IQListener {

	enum IQType {
		result, error
	}

	void handleIQ(IQ iq);

}
