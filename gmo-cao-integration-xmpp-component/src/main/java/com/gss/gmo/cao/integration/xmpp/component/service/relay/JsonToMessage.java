package com.gss.gmo.cao.integration.xmpp.component.service.relay;

import org.springframework.beans.BeanUtils;
import org.xmpp.packet.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToMessage {

	private ObjectMapper mapper = new ObjectMapper();

	public Message convert(String json) throws Exception {
		PureMessage originMessage = mapper.readValue(json, PureMessage.class);
		Message message = new Message();
		BeanUtils.copyProperties(originMessage, message);
		message.setFrom(originMessage.getFrom());
		message.setTo(originMessage.getTo());
		return message;
	}

}
