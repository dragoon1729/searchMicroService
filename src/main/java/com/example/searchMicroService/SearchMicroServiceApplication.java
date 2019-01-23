package com.example.searchMicroService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@SpringBootApplication
@EnableSolrRepositories
public class SearchMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchMicroServiceApplication.class, args);
	}


}

