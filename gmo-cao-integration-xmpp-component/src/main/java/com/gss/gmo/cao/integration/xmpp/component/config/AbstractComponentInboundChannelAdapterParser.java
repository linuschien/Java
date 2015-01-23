package com.gss.gmo.cao.integration.xmpp.component.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.AbstractChannelAdapterParser;
import org.springframework.integration.config.xml.IntegrationNamespaceUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author linus_chien
 *
 */
public abstract class AbstractComponentInboundChannelAdapterParser extends AbstractChannelAdapterParser {

	@Override
	protected boolean shouldGenerateIdAsFallback() {
		return true;
	}

	@Override
	protected AbstractBeanDefinition doParse(Element element, ParserContext parserContext, String channelName) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(getEndpointClassName());

		String componentManagerName = element.getAttribute("component-manager");
		if (StringUtils.hasText(componentManagerName)) {
			builder.addConstructorArgReference(componentManagerName);
		} else if (parserContext.getRegistry().containsBeanDefinition(XmppComponentNamespaceHandler.XMPP_COMPONENT_MANAGER_BEAN_NAME)) {
			builder.addConstructorArgReference(XmppComponentNamespaceHandler.XMPP_COMPONENT_MANAGER_BEAN_NAME);
		} else {
			throw new BeanCreationException("You must either explicitly define which Component Manager to use via "
					+ "'component-manager' attribute or have default Component Manager bean registered under the name 'xmppComponentManager'"
					+ "(e.g., <int-xmpp-component:component-manager .../>). If 'id' is not provided the default will be 'xmppComponentManager'.");
		}

		builder.addPropertyReference("outputChannel", channelName);
		IntegrationNamespaceUtils.setReferenceIfAttributeDefined(builder, element, "error-channel");

		return builder.getBeanDefinition();
	}

	protected abstract String getEndpointClassName();
}
