package com.ruckuswireless.tdc.cassandra.lab;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.ruckuswireless.tdc.cassandra.lab.model.Person;
import com.ruckuswireless.tdc.cassandra.lab.repo.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/ruckuswireless/tdc/cassandra/lab/spring-context.xml")
@DirtiesContext
public class LabTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void test() {
		Person person = new Person(UUIDs.timeBased(), "Linus", 40);
		person = personRepository.save(person);
		System.out.println(person.getId());
	}

}
