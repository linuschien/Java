package com.gss.gmo.cao.integration.xmpp.component.config;

import static reactor.util.StringUtils.hasText;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author linus_chien
 *
 */
public class IQInboundChannelAdapterParser extends AbstractComponentInboundChannelAdapterParser {

	@Override
	protected String getEndpointClassName() {
		return "com.gss.gmo.cao.integration.xmpp.component.inbound.IQListeningEndpoint";
	}

	@Override
	protected AbstractBeanDefinition doParse(Element element, ParserContext parserContext, String channelName) {
		AbstractBeanDefinition beanDefinition = super.doParse(element, parserContext, channelName);
		String iqType = element.getAttribute("iq-type");
		if (hasText(iqType)) {
			beanDefinition.getPropertyValues().addPropertyValue("iqType", iqType);
		} else {
			throw new BeanCreationException("IQType must be set.");
		}
		return beanDefinition;
	}

}
