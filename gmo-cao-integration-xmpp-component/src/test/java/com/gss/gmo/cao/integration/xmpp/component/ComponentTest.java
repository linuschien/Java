package com.gss.gmo.cao.integration.xmpp.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmpp.component.AbstractComponent;
import org.xmpp.component.Component;
import org.xmpp.component.ComponentException;
import org.xmpp.component.ComponentManager;
import org.xmpp.packet.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ComponentTest {

	private final String testMessage = "Test by Linus";
	private String result;

	@Autowired
	private ComponentManager componentManager;

	@Test
	public void test() {
		try {
			Component component = new AbstractComponent() {

				@Override
				protected void handleMessage(Message message) {
					log.info(message.getBody());
					result = message.getBody();
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

			};
			componentManager.addComponent("ext", component);
			Message message = new Message();
			message.setTo("linus_chien@ext.im.gss.com.tw");
			message.setBody(testMessage);
			componentManager.sendPacket(component, message);
		} catch (ComponentException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(testMessage, result);
	}

}
