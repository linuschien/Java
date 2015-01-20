package com.gss.gmo.cao.integration.xmpp.component.core;

import org.jivesoftware.whack.ExternalComponentManager;
import org.xmpp.component.Component;
import org.xmpp.component.ComponentException;
import org.xmpp.packet.Packet;

/**
 * @author linus_chien
 *
 */
public class SingleSubdomainComponentManager extends ExternalComponentManager {

	private String subdomain;
	private GlobalComponent component;

	public SingleSubdomainComponentManager(String host, int port, String subdomain, String secretKey) throws ComponentException {
		super(host, port);
		this.subdomain = subdomain;
		this.setSecretKey(subdomain, secretKey);
		this.component = new GlobalComponent(subdomain, "Spring Integration XMPP Component");
		this.addComponent(subdomain, component);
	}

	public Component getComponent() {
		return component;
	}

	public void close() throws ComponentException {
		this.removeComponent(subdomain);
	}

	public void sendPacket(Packet packet) {
		super.sendPacket(component, packet);
	}

}
