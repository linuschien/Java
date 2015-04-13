package com.ruckuswireless.tdc.elasticsearch.lab;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/ruckuswireless/tdc/elasticsearch/lab/spring-context.xml")
@DirtiesContext
public class LabTest {

	@Autowired
	private ElasticsearchOperations elasticsearchTemplate;

	@Test
	public void test() {
		Boolean isExist = elasticsearchTemplate.indexExists("Book");
		assertFalse(isExist);
	}

}
