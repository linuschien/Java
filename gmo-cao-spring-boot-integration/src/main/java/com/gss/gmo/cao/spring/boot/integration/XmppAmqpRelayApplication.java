package com.gss.gmo.cao.spring.boot.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@ImportResource(value = "classpath:**/XmppAmqpRelayApplication-context.xml")
public class XmppAmqpRelayApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmppAmqpRelayApplication.class, args);
	}

}
