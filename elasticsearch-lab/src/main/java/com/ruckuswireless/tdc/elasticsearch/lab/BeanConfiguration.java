package com.ruckuswireless.tdc.elasticsearch.lab;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
public class BeanConfiguration {

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}

	@Bean
	public Client client() {
		return NodeBuilder.nodeBuilder().node().client();
	}

}
