package com.example.searchMicroService;

import com.example.searchMicroService.entity.Product;
import com.example.searchMicroService.repository.MySolrRepository;
import com.example.searchMicroService.services.SolrServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MokitoServiceTester {
    @InjectMocks
    SolrServiceImpl service;
    @Mock
    MySolrRepository repository;
    @Test
    public void testAdd()
    {
        Product product=new Product();
        product.setProductId("123");
        Mockito.when(repository.save((Mockito.any(Product.class)))).thenReturn(product);
        Product product1=service.save(new Product());
        Assert.assertNotNull(product1);
    }
    @Test
    public void testSearchByCategory()
    {
        List<Product> productList=new ArrayList<>();
        productList.add(new Product());
        Mockito.when(repository.findByNameOrCategory(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(productList);
        List<Product> products=repository.findByNameOrCategory("mobile","mobile");
        Assert.assertTrue(products.size()!=0);
    }
    @Test
    public  void testSearch()
    {
        List<Product> productList=new ArrayList<>();
        productList.add(new Product());
        Mockito.when(repository.findByCustomQuery(Mockito.any(String.class))).thenReturn(productList);
        List<Product> products=repository.findByCustomQuery("iphone6s");
        Assert.assertTrue(products.size()!=0);

    }
}
