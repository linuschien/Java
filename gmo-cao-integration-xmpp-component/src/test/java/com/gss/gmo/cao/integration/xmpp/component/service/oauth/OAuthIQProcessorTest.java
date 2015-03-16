package com.gss.gmo.cao.integration.xmpp.component.service.oauth;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class OAuthIQProcessorTest {

	@Value("${user.name}")
	private String name;

	@Value("${user.password}")
	private String password;

	@Test
	public void testGetRequestToken() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				System.out.println("^^^^^Check Server Cert...");
			}
		}

		};
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, trustAllCerts, null);

		ConnectionConfiguration config = new ConnectionConfiguration("im.gss.com.tw", 5222);
		config.setCustomSSLContext(ctx);
		config.setDebuggerEnabled(true);

		XMPPConnection con = new XMPPTCPConnection(config);
		con.connect();
		con.login(name, password, "Smack");

		final IQ iq = new IQ() {
			@Override
			public String getChildElementXML() {
				return "<oauth xmlns='get_request_token'/>";
			}
		};
		iq.setTo("ext.im.gss.com.tw");
		iq.setType(IQ.Type.GET);
		iq.setPacketID("xxxxxxxxxx");
		con.sendPacket(iq);

		con.addPacketListener(new PacketListener() {
			@Override
			public void processPacket(Packet packet) throws NotConnectedException {
				if ("xxxxxxxxxx".equals(packet.getPacketID())) {
					if (packet instanceof IQ) {
						System.out.println(((IQ) packet).getChildElementXML());
					}
				}
			}
		}, null);

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
