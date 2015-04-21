package com.ruckuswireless.tdc.elasticsearch.lab;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/ruckuswireless/tdc/elasticsearch/lab/spring-context.xml")
@DirtiesContext
public class LabTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private Client client;

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

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SearchResponse reponse = client.prepareSearch("users").setTypes("user")
				.setQuery(QueryBuilders.filteredQuery(QueryBuilders.termQuery("name", "linus"), FilterBuilders.termFilter("email", "linus.chien")))
				.setExplain(true).execute().actionGet();

		for (SearchHit hit : reponse.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
			System.out.println(hit.explanation().toString());
			ObjectMapper mapper = new ObjectMapper();
			try {
				Users value = mapper.readValue(hit.getSourceAsString(), Users.class);
				System.out.println(value.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
