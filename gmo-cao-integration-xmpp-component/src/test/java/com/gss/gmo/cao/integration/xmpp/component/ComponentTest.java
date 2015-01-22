package com.gss.gmo.cao.integration.xmpp.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmpp.packet.Message;

import com.gss.gmo.cao.integration.xmpp.component.core.ComponentManager;
import com.gss.gmo.cao.integration.xmpp.component.core.MessageListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class ComponentTest {

	private final String testMessage = "Test by Linus";
	private String result;

	@Autowired
	private ComponentManager componentManager;

	@Autowired
	@Qualifier("message-outbound-channel")
	private MessageChannel messageOutboundChannel;

	@Test
	public void test() {
		componentManager.setMessageListener(new MessageListener() {
			@Override
			public void handleMessage(Message message) {
				result = message.getBody();
			}
		});

		Message message = new Message();
		message.setTo("linus_chien@ext.im.gss.com.tw");
		message.setBody(testMessage);
		org.springframework.messaging.Message<Message> outboundMessage = MessageBuilder.withPayload(message).build();
		messageOutboundChannel.send(outboundMessage);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(testMessage, result);
	}

}
