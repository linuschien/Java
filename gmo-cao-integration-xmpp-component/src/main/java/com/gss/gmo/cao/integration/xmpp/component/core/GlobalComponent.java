package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.component.AbstractComponent;
import org.xmpp.packet.Message;

/**
 * @author linus_chien
 *
 */
public class GlobalComponent extends AbstractComponent {

	private String name;
	private String description;

	private MessageListener messageListener;

	public GlobalComponent(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	@Override
	protected void handleMessage(Message message) {
		if (messageListener != null) {
			messageListener.handleMessage(message);
		}
	}

}
