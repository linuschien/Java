package com.gss.gmo.cao.integration.xmpp.component.config;

import static reactor.util.StringUtils.hasText;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.IntegrationNamespaceUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class IQInboundGatewayParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected String getBeanClassName(Element element) {
		return "com.gss.gmo.cao.integration.xmpp.component.inbound.IQHandlerGateway";
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
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

		String iqType = element.getAttribute("iq-type");
		if (hasText(iqType)) {
			builder.addPropertyValue("iqType", iqType);
		} else {
			throw new BeanCreationException("IQType must be set.");
		}

		String inputChannelAttributeName = "request-channel";
		String inputChannelRef = element.getAttribute(inputChannelAttributeName);
		if (!StringUtils.hasText(inputChannelRef)) {
			parserContext.getReaderContext().error("a '" + inputChannelAttributeName + "' reference is required", element);
		}
		builder.addPropertyReference("requestChannel", inputChannelRef);

		IntegrationNamespaceUtils.setReferenceIfAttributeDefined(builder, element, "reply-channel");

		IntegrationNamespaceUtils.setReferenceIfAttributeDefined(builder, element, "error-channel");
	}
}
