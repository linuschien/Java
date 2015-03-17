package com.gss.gmo.cao.integration.xmpp.component.service;

import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;

public abstract class AbstractIQProcessor implements IQProcessor {

	protected IQProcessor successor;

	@Override
	public abstract IQ process(IQ iq);

	@Override
	public void setSuccessor(IQProcessor successor) {
		this.successor = successor;
	}

	protected void swapFromTo(IQ iq) {
		JID from = iq.getFrom();
		JID to = iq.getTo();
		iq.setFrom(to);
		iq.setTo(from);
	}

	protected abstract boolean isResponsible(IQ iq);

}
