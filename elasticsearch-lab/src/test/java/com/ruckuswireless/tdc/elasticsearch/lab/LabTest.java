package com.ruckuswireless.tdc.elasticsearch.lab;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/ruckuswireless/tdc/elasticsearch/lab/spring-context.xml")
@DirtiesContext
public class LabTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void test() {
		Boolean isExist = elasticsearchTemplate.indexExists("Book");
		assertFalse(isExist);
		elasticsearchTemplate.deleteIndex(Users.class);
		boolean isSuccess = elasticsearchTemplate.createIndex(Users.class);
		assertTrue(isSuccess);
		
		Users user = new Users();
		user.setName("Linus Chien");
		user.setEmail("linus.chien@gmail.com");
		user.setDescription("I am Linus");
		IndexQuery indexQuery = new IndexQueryBuilder().withObject(user).build();
		elasticsearchTemplate.index(indexQuery);
	}

}
