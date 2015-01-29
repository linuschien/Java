package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.ServiceActivator;
import org.xmpp.packet.IQ;

public class InboundIQService {

	private ComponentTest componentTest;

	void setComponentTest(ComponentTest componentTest) {
		this.componentTest = componentTest;
	}

	@ServiceActivator
	public void handleResultIQ(IQ iq) {
		componentTest.setResultChildElementName(iq.getChildElement().getName());
	}

	@ServiceActivator
	public void handleErrorIQ(IQ iq) {
		componentTest.setErrorChildElementName(iq.getChildElement().getName());
	}

}
