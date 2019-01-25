package com.example.searchMicroService;

import com.example.searchMicroService.DTO.ProductDTO;
import com.example.searchMicroService.controller.SolrController;
import com.example.searchMicroService.entity.Product;
import com.example.searchMicroService.services.SolrServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class MockitoControllerTester {
    @InjectMocks
    private SolrController controller;
    @Mock
    private SolrServiceImpl service;
    private MockMvc mvc;

    @Before
    public void init() {

        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testgetByCategory() throws Exception {
        Mockito.when(service.findByCategory("mobile","mobile")).thenReturn(new ArrayList<>());
        this.mvc.perform(

                MockMvcRequestBuilders.get("/search/getByCategory").param("querypara","mobile")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(service).findByCategory(Mockito.anyString(),Mockito.anyString());
    }
    @Test
    public void testsearchByName() throws Exception {
        Mockito.when(service.findByCustomQuery("mobile")).thenReturn(new ArrayList<>());
        this.mvc.perform(

                MockMvcRequestBuilders.get("/search/searchByName").param("querypara","mobile")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(service).findByCustomQuery(Mockito.anyString());
    }

    @Test
    public void testPostProduct() throws Exception {
        ProductDTO request=new ProductDTO();
        request.setName("iphone6s");
        Mockito.when(service.save(Mockito.any(Product.class))).thenReturn(new Product());
        ObjectMapper mapper=new ObjectMapper();
        this.mvc.perform(
                MockMvcRequestBuilders.post("/search/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(service).save(Mockito.any(Product.class));

    }
}
