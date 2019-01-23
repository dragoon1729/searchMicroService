package com.example.searchMicroService.services;

import com.example.searchMicroService.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SolrService {
    Optional select(String querypara);
    Product save(Product product);
    void delete(Product product);
    Product update(Product product);
    void deleteById(String productId);
    List<Product> findByName(String name);
    List<Product> findByNameOrCategory(String name,String category);
    List<Product> findByCustomQuery(String searchTerm);
}
