package com.example.searchMicroService.repository;

import com.example.searchMicroService.entity.Product;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySolrRepository extends SolrCrudRepository<Product, String> {

    List<Product> findByName(String name);
    List<Product> findByNameOrCategory(String name,String category);
    @Query("name:*?0* OR category:*?0*")
    List<Product> findByCustomQuery(String searchTerm);

}
