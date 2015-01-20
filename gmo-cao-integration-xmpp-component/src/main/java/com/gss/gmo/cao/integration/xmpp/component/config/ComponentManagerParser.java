package com.gss.gmo.cao.integration.xmpp.component.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author linus_chien
 *
 */
public class ComponentManagerParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected String getBeanClassName(Element element) {
		return "com.gss.gmo.cao.integration.xmpp.component.core.SingleSubdomainComponentManager";
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String host = element.getAttribute("host");
		String port = element.getAttribute("port");
		String subdomain = element.getAttribute("subdomain");
		String secretKey = element.getAttribute("secretKey");
		builder.addConstructorArgValue(host);
		builder.addConstructorArgValue(port);
		builder.addConstructorArgValue(subdomain);
		builder.addConstructorArgValue(secretKey);
		builder.setDestroyMethodName("close");
	}

}
