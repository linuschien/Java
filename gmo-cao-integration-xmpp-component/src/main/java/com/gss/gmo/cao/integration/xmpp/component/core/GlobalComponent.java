package com.gss.gmo.cao.integration.xmpp.component.core;

import org.xmpp.component.AbstractComponent;
import org.xmpp.packet.IQ;
import org.xmpp.packet.Message;
import org.xmpp.packet.Presence;

/**
 * @author linus_chien
 *
 */
public class GlobalComponent extends AbstractComponent {

	private String name;
	private String description;

	private MessageListener messageListener;
	private PresenceListener presenceListener;
	private IQListener resultIQListener;
	private IQListener errorIQListener;

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

	public void setPresenceListener(PresenceListener presenceListener) {
		this.presenceListener = presenceListener;
	}

	public void setResultIQListener(IQListener resultIQListener) {
		this.resultIQListener = resultIQListener;
	}

	public void setErrorIQListener(IQListener errorIQListener) {
		this.errorIQListener = errorIQListener;
	}

	@Override
	protected void handleMessage(Message message) {
		if (messageListener != null) {
			messageListener.handleMessage(message);
		}
	}

	@Override
	protected void handlePresence(Presence presence) {
		if (presenceListener != null) {
			presenceListener.handlePresence(presence);
		}
	}

	@Override
	protected void handleIQResult(IQ iq) {
		if (resultIQListener != null) {
			resultIQListener.handleIQ(iq);
		}
	}

	@Override
	protected void handleIQError(IQ iq) {
		if (errorIQListener != null) {
			errorIQListener.handleIQ(iq);
		} else {
			super.handleIQError(iq);
		}
	}

}
