package com.example.searchMicroService.services;

import com.example.searchMicroService.entity.Product;
import com.example.searchMicroService.repository.MySolrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    MySolrRepository mySolrRepository;

    @Override
    public Optional select(String querypara) {
      return mySolrRepository.findById(querypara);

    }

    @Override
    public Product save(Product product) {
        return mySolrRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        mySolrRepository.delete(product);
    }

    @Override
    public Product update(Product product) {
        return mySolrRepository.save(product);
    }

    @Override
    public void deleteById(String productId) {
        mySolrRepository.deleteById(productId);
    }

    @Override
    public List<Product> findByName(String name) {
        return mySolrRepository.findByName(name);
    }

    @Override
    public List<Product> findByNameOrCategory(String name, String category) {
        return mySolrRepository.findByNameOrCategory(name,category);
    }

    @Override
    public List<Product> findByCustomQuery(String searchTerm) {
        return mySolrRepository.findByCustomQuery(searchTerm);
    }

}
