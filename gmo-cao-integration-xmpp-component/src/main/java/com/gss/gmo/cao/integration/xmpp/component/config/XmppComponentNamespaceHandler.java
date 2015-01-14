package com.gss.gmo.cao.integration.xmpp.component.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author linus_chien
 *
 */
public class XmppComponentNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("component-manager", new ComponentManagerParser());
	}

}
