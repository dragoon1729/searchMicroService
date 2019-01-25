package com.example.searchMicroService;

import com.example.searchMicroService.entity.Product;
import com.example.searchMicroService.repository.MySolrRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)

@SpringBootTest

public class MockitoRepositoryTester {



    @Autowired

    private MySolrRepository productRepository;



    @Test()
    public void findByCategory()

    {

       List<Product> products=productRepository.findByCategory("mobile","mobile");

        Assert.assertNotNull(products);

    }



    @Test

    public void findByCustomQuery()

    {

        List<Product> list=productRepository.findByCustomQuery("a");

        Assert.assertNotNull(list);

    }

}