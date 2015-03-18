package com.gss.gmo.cao.integration.xmpp.component.service.oauth;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth.consumer.OAuthConsumerSupport;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.OAuthSecurityContext;
import org.springframework.security.oauth.consumer.OAuthSecurityContextHolder;
import org.springframework.security.oauth.consumer.OAuthSecurityContextImpl;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.springframework.security.oauth.consumer.client.OAuthClientHttpRequestFactory;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class OAuthTest {

	private OAuthConsumerSupport support = new CoreOAuthConsumerSupport();

	@Value("${access.token.value}")
	private String value;

	@Value("${access.token.secret}")
	private String secret;

	@Autowired
	@Qualifier("vitalsEsp")
	private ProtectedResourceDetails vitalsEsp;

	@Test
	public void test() {
		OAuthConsumerToken accessToken = new OAuthConsumerToken();
		accessToken.setAccessToken(true);
		accessToken.setValue(value);
		accessToken.setSecret(secret);
		OAuthSecurityContext context = OAuthSecurityContextHolder.getContext();
		if (context == null) {
			context = new OAuthSecurityContextImpl();
			OAuthSecurityContextHolder.setContext(context);
		}

		Map<String, OAuthConsumerToken> accessTokens = context.getAccessTokens();
		if (accessTokens == null) {
			accessTokens = new HashMap<String, OAuthConsumerToken>();
			((OAuthSecurityContextImpl) context).setAccessTokens(accessTokens);
		}
		accessTokens.put(vitalsEsp.getId(), accessToken);

		OAuthClientHttpRequestFactory requestFactory = new OAuthClientHttpRequestFactory(new SimpleClientHttpRequestFactory(), vitalsEsp, support);
		OAuthRestTemplate rest = new OAuthRestTemplate(requestFactory, vitalsEsp);
		String resourceURL = "http://gsskm.gss.com.tw/km/api2/search/simple/{keyword}?pi={pi}&ps={ps}";
		String result = rest.getForEntity(resourceURL, String.class, "REST", 0, 10).getBody();
		System.out.println(result);
	}

}
