package com.ruckuswireless.tdc.elasticsearch.lab;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "users", indexStoreType = "mmapfs", refreshInterval = "5s", replicas = 1, shards = 1, type = "user")
public class Users {

	@Id
	private String id;

	private String name;

	private String email;

	private String description;
}
