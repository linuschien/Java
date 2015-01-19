package com.gss.gmo.cao.integration.xmpp.component.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author linus_chien
 *
 */
public class XmppComponentNamespaceHandler extends NamespaceHandlerSupport {

	public static final String XMPP_COMPONENT_MANAGER_BEAN_NAME = "xmppComponentManager";

	@Override
	public void init() {
		registerBeanDefinitionParser("component-manager", new ComponentManagerParser());
	}

}
