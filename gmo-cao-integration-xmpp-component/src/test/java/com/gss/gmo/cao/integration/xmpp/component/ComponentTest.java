package com.gss.gmo.cao.integration.xmpp.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmpp.packet.Message;
import org.xmpp.packet.Presence;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class ComponentTest {

	private final String testMessage = "Test by Linus";
	private final String testStatus = "Linus On-line";

	private String result;
	private String status;

	@Autowired
	private InboundMessageService inboundMessageService;

	@Autowired
	private OutboundMessageService outboundMessageService;

	@Autowired
	private InboundPresenceService inboundPresenceService;

	@Autowired
	private OutboundPresenceService outboundPresenceService;

	@Test
	public void test() {
		inboundMessageService.setComponentTest(this);
		inboundPresenceService.setComponentTest(this);

		Message message = new Message();
		message.setTo("linus_chien@ext.im.gss.com.tw");
		message.setBody(testMessage);
		outboundMessageService.send(message);

		Presence presence = new Presence();
		presence.setTo("linus_chien@ext.im.gss.com.tw");
		presence.setStatus(testStatus);
		outboundPresenceService.send(presence);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(testMessage, result);
		assertEquals(testStatus, status);
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
