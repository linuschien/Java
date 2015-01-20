package com.gss.gmo.cao.integration.xmpp.component.core;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.integration.handler.AbstractMessageHandler;

import com.gss.gmo.cao.integration.xmpp.component.config.XmppComponentNamespaceHandler;

/**
 * @author linus_chien
 *
 */
public abstract class AbstractComponentManagerAwareMessageHandler extends AbstractMessageHandler {

	protected SingleSubdomainComponentManager componentManager;

	protected boolean initialized;

	public AbstractComponentManagerAwareMessageHandler(SingleSubdomainComponentManager componentManager) {
		notNull(componentManager, "ComponentManager must not be null");
		this.componentManager = componentManager;
	}

	protected void onInit() throws Exception {
		BeanFactory beanFactory = this.getBeanFactory();
		if (this.componentManager == null && beanFactory != null) {
			this.componentManager = beanFactory.getBean(XmppComponentNamespaceHandler.XMPP_COMPONENT_MANAGER_BEAN_NAME, SingleSubdomainComponentManager.class);
		}
		notNull(this.componentManager, "Failed to resolve ComponentManager. ComponentManager must either be set explicitly "
				+ "via 'component-manager' attribute or implicitly by registering a bean with the name 'xmppComponentManager' and of type "
				+ "'org.jivesoftware.whack.ExternalComponentManager' in the Application Context.");
		this.initialized = true;
	}

}
