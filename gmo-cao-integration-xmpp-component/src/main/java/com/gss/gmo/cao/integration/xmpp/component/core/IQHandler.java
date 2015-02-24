package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.packet.IQ;

public interface IQHandler {

	enum IQType {
		get, set
	}

	IQ handleIQ(IQ iq);

}
