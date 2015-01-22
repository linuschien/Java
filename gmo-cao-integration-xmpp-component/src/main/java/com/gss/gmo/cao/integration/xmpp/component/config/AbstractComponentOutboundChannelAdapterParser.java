package com.gss.gmo.cao.integration.xmpp.component.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.AbstractOutboundChannelAdapterParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author linus_chien
 *
 */
public abstract class AbstractComponentOutboundChannelAdapterParser extends AbstractOutboundChannelAdapterParser {

	@Override
	protected AbstractBeanDefinition parseConsumer(Element element, ParserContext parserContext) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.getHandlerClassName());

		String connectionName = element.getAttribute("component-manager");
		if (StringUtils.hasText(connectionName)) {
			builder.addConstructorArgReference(connectionName);
		} else if (parserContext.getRegistry().containsBeanDefinition(XmppComponentNamespaceHandler.XMPP_COMPONENT_MANAGER_BEAN_NAME)) {
			builder.addConstructorArgReference(XmppComponentNamespaceHandler.XMPP_COMPONENT_MANAGER_BEAN_NAME);
		} else {
			throw new BeanCreationException("You must either explicitly define which Component Manager to use via "
					+ "'component-manager' attribute or have default Component Manager bean registered under the name 'xmppComponentManager'"
					+ "(e.g., <int-xmpp-component:component-manager .../>). If 'id' is not provided the default will be 'xmppComponentManager'.");
		}

		return builder.getBeanDefinition();
	}

	protected abstract String getHandlerClassName();

}
