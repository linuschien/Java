package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.ServiceActivator;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;

public class IQGatewayService {

	@ServiceActivator
	public IQ handleGetIQ(IQ iq) {
		iq.setType(Type.result);
		return iq;
	}

}
