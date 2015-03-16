package com.gss.gmo.cao.integration.xmpp.component.service;

import org.xmpp.packet.IQ;

public interface IQProcessor {

	IQ process(IQ iq);

	void setSuccessor(IQProcessor successor);

}
