package com.gss.gmo.cao.integration.xmpp.component.core;

import static com.gss.gmo.cao.integration.xmpp.component.core.IQListener.IQType.error;
import static com.gss.gmo.cao.integration.xmpp.component.core.IQListener.IQType.result;

import java.util.EnumMap;

import org.xmpp.component.AbstractComponent;
import org.xmpp.packet.IQ;
import org.xmpp.packet.Message;
import org.xmpp.packet.Presence;

import com.gss.gmo.cao.integration.xmpp.component.core.IQListener.IQType;

/**
 * @author linus_chien
 *
 */
public class GlobalComponent extends AbstractComponent {

	private String name;
	private String description;

	private MessageListener messageListener;
	private PresenceListener presenceListener;
	private EnumMap<IQType, IQListener> iqListenerMap = new EnumMap<IQType, IQListener>(IQType.class);
	private EnumMap<IQHandler.IQType, IQHandler> iqHandlerMap = new EnumMap<IQHandler.IQType, IQHandler>(IQHandler.IQType.class);

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

	public void addIQListener(IQType iqType, IQListener iqListener) {
		this.iqListenerMap.put(iqType, iqListener);
	}

	public void addIQHandler(IQHandler.IQType iqType, IQHandler iqHandler) {
		this.iqHandlerMap.put(iqType, iqHandler);
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
		if (iqListenerMap.get(result) != null) {
			iqListenerMap.get(result).handleIQ(iq);
		}
	}

	@Override
	protected void handleIQError(IQ iq) {
		if (iqListenerMap.get(error) != null) {
			iqListenerMap.get(error).handleIQ(iq);
		} else {
			super.handleIQError(iq);
		}
	}

	@Override
	protected IQ handleIQGet(IQ iq) {
		if (iqHandlerMap.get(IQHandler.IQType.get) != null) {
			return iqHandlerMap.get(IQHandler.IQType.get).handleIQ(iq);
		} else {
			return null;
		}
	}

	@Override
	protected IQ handleIQSet(IQ iq) {
		if (iqHandlerMap.get(IQHandler.IQType.set) != null) {
			return iqHandlerMap.get(IQHandler.IQType.set).handleIQ(iq);
		} else {
			return null;
		}
	}

}
