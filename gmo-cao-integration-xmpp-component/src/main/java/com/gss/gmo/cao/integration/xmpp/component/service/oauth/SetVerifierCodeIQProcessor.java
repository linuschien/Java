package com.gss.gmo.cao.integration.xmpp.component.service.oauth;

import org.dom4j.Element;
import org.springframework.security.oauth.consumer.OAuthConsumerSupport;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.ProtectedResourceDetailsService;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;

import com.gss.gmo.cao.integration.xmpp.component.service.AbstractIQProcessor;

public class SetVerifierCodeIQProcessor extends AbstractIQProcessor {

	private OAuthConsumerSupport support = new CoreOAuthConsumerSupport();

	private ProtectedResourceDetailsService protectedResourceDetailsService;

	private String resourceId;

	@Override
	public IQ process(IQ iq) {
		if (isResponsible(iq)) {
			ProtectedResourceDetails protectedResourceDetails = protectedResourceDetailsService.loadProtectedResourceDetailsById(resourceId);
			Element childElement = iq.getChildElement();
			String value = childElement.attributeValue("value");
			String secret = childElement.attributeValue("secret");
			String verfier = childElement.attributeValue("verifier");

			OAuthConsumerToken requestToken = new OAuthConsumerToken();
			requestToken.setAccessToken(false);
			requestToken.setValue(value);
			requestToken.setSecret(secret);

			OAuthConsumerToken accessToken = support.getAccessToken(protectedResourceDetails, requestToken, verfier);
			value = accessToken.getValue();
			secret = accessToken.getSecret();

			swapFromTo(iq);
			iq.setType(Type.result);
			Element result = iq.setChildElement("oauth", "access_token");
			result.addAttribute("value", value);
			result.addAttribute("secret", secret);
			return iq;
		}
		return successor.process(iq);
	}

	public void setProtectedResourceDetailsService(ProtectedResourceDetailsService protectedResourceDetailsService) {
		this.protectedResourceDetailsService = protectedResourceDetailsService;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	protected boolean isResponsible(IQ iq) {
		if (!iq.getType().equals(Type.set)) {
			return false;
		}

		Element childElement = iq.getChildElement();
		if (!"oauth".equals(childElement.getName())) {
			return false;
		}
		if (!"set_verifier_code".equals(childElement.getNamespace().getStringValue())) {
			return false;
		}

		return true;
	}

}
