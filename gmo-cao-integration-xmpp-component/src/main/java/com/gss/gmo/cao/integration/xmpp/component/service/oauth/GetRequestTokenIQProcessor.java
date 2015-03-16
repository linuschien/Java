package com.gss.gmo.cao.integration.xmpp.component.service.oauth;

import org.dom4j.Element;
import org.springframework.security.oauth.consumer.OAuthConsumerSupport;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;

import com.gss.gmo.cao.integration.xmpp.component.service.AbstractIQProcessor;

public class GetRequestTokenIQProcessor extends AbstractIQProcessor {

	private OAuthConsumerSupport support = new CoreOAuthConsumerSupport();

	private ProtectedResourceDetails protectedResourceDetails;

	@Override
	public IQ process(IQ iq) {
		if (iq.getType().equals(Type.get)) {
			Element childElement = iq.getChildElement();
			if ("oauth:get_request_token".equals(iq.getChildElement().getNamespace())) {
				OAuthConsumerToken requestToken = support.getUnauthorizedRequestToken(protectedResourceDetails, "oob");
				String value = requestToken.getValue();
				String secret = requestToken.getSecret();
				swapFromTo(iq);
				iq.setType(Type.result);
				childElement.addAttribute("value", value);
				childElement.addAttribute("secret", secret);
				childElement.addAttribute("user-authorization-url", protectedResourceDetails.getUserAuthorizationURL());
				return iq;
			}
		}
		return successor.process(iq);
	}

	public void setProtectedResourceDetails(ProtectedResourceDetails protectedResourceDetails) {
		this.protectedResourceDetails = protectedResourceDetails;
	}

}
