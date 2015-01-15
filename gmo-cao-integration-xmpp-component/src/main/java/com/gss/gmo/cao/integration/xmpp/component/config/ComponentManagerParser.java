package com.gss.gmo.cao.integration.xmpp.component.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author linus_chien
 *
 */
public class ComponentManagerParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected String getBeanClassName(Element element) {
		return "org.jivesoftware.whack.ExternalComponentManager";
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String host = element.getAttribute("host");
		String port = element.getAttribute("port");
		String defaultSecretKey = element.getAttribute("defaultSecretKey");
		builder.addConstructorArgValue(host);
		builder.addConstructorArgValue(port);
		if (StringUtils.hasText(defaultSecretKey)) {
			builder.addPropertyValue("defaultSecretKey", defaultSecretKey);
		}
	}

}
