package com.gss.gmo.cao.integration.xmpp.component.core;

import java.util.EnumMap;

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
	private EnumMap<IQListener.Type, IQListener> iqListenerMap = new EnumMap<IQListener.Type, IQListener>(IQListener.Type.class);

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

	public void addIQListener(IQListener.Type type, IQListener iqListener) {
		this.iqListenerMap.put(type, iqListener);
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
		if (iqListenerMap.get(IQListener.Type.result) != null) {
			iqListenerMap.get(IQListener.Type.result).handleIQ(iq);
		}
	}

	@Override
	protected void handleIQError(IQ iq) {
		if (iqListenerMap.get(IQListener.Type.error) != null) {
			iqListenerMap.get(IQListener.Type.error).handleIQ(iq);
		} else {
			super.handleIQError(iq);
		}
	}

}
