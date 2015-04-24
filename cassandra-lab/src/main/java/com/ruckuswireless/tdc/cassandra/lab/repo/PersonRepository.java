package com.ruckuswireless.tdc.cassandra.lab.repo;

import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;
import org.springframework.stereotype.Repository;

import com.ruckuswireless.tdc.cassandra.lab.model.Person;

@Repository
public interface PersonRepository extends TypedIdCassandraRepository<Person, String> {

}
