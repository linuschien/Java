package com.gss.gmo.cao.integration.xmpp.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmpp.packet.Message;

import com.gss.gmo.cao.integration.xmpp.component.core.SingleSubdomainComponentManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class ComponentTest {

	private final String testMessage = "Test by Linus";
	private String result;

	@Autowired
	private SingleSubdomainComponentManager componentManager;

	@Test
	public void test() {

		Message message = new Message();
		message.setTo("linus_chien@ext.im.gss.com.tw");
		message.setBody(testMessage);
		componentManager.sendPacket(message);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(testMessage, result);
	}

}
