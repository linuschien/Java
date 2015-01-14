package com.gss.gmo.cao.integration.xmpp.component;

import org.jivesoftware.whack.ExternalComponentManager;
import org.junit.Test;
import org.xmpp.component.AbstractComponent;
import org.xmpp.component.ComponentException;
import org.xmpp.packet.Message;

public class ComponentTest {

	@Test
	public void test() {
		ExternalComponentManager componentManager = new ExternalComponentManager("im.gss.com.tw", 5275);
		componentManager.setDefaultSecretKey("gss1234");
		try {
			componentManager.addComponent("ext", new AbstractComponent() {

				@Override
				protected void handleMessage(Message message) {
					log.info(message.getBody());
					log.info(getJID().toString());
				}

				@Override
				public String getName() {
					return "linus";
				}

				@Override
				public String getDescription() {
					return "Linus External Component.";
				}
			});
		} catch (ComponentException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
