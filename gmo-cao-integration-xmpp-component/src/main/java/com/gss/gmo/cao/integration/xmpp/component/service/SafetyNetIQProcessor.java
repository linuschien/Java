package com.gss.gmo.cao.integration.xmpp.component.service;

import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;

public class SafetyNetIQProcessor extends AbstractIQProcessor {

	@Override
	public IQ process(IQ iq) {
		swapFromTo(iq);
		iq.setType(Type.error);
		return iq;
	}

}
