package com.gss.gmo.cao.integration.xmpp.component.core;

import org.jivesoftware.whack.ExternalComponentManager;
import org.xmpp.component.ComponentException;
import org.xmpp.packet.Packet;

/**
 * @author linus_chien
 *
 */
public class SingleSubdomainComponentManager {

	private ExternalComponentManager componentManager;
	private String subdomain;
	private GlobalComponent component;

	public SingleSubdomainComponentManager(String host, int port, String subdomain, String secretKey) throws ComponentException {
		this.componentManager = new ExternalComponentManager(host, port);
		this.subdomain = subdomain;
		componentManager.setSecretKey(subdomain, secretKey);
		this.component = new GlobalComponent(subdomain, "Spring Integration XMPP Component");
		componentManager.addComponent(subdomain, component);
	}

	public void close() throws ComponentException {
		componentManager.removeComponent(subdomain);
	}

	public void sendPacket(Packet packet) {
		componentManager.sendPacket(component, packet);
	}

	public void setMessageListener(MessageListener messageListener) {
		component.setMessageListener(messageListener);
	}

}
