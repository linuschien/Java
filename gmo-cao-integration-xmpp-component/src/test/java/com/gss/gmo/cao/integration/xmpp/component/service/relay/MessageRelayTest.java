package com.gss.gmo.cao.integration.xmpp.component.service.relay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class MessageRelayTest {

	@Value("${rabbitmq.queue.name}")
	private String queue;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test() throws Exception {
		PureMessage message = new PureMessage();
		message.setTo("linus_chien@im.gss.com.tw/Pidgin");
		message.setFrom("ext.im.gss.com.tw");
		message.setBody("Test from RabbitMQ.");

		String payload = mapper.writeValueAsString(message);

		System.out.println("payload:" + payload);
		rabbitTemplate.convertAndSend(queue, payload);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
