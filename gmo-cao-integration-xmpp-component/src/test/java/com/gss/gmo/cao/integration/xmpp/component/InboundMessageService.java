package com.gss.gmo.cao.integration.xmpp.component;

import org.springframework.integration.annotation.ServiceActivator;
import org.xmpp.packet.Message;

public class InboundMessageService {

	private ComponentTest componentTest;

	void setComponentTest(ComponentTest componentTest) {
		this.componentTest = componentTest;
	}

	@ServiceActivator
	public void handleMessage(Message message) {
		componentTest.setResult(message.getBody());
	}

}
