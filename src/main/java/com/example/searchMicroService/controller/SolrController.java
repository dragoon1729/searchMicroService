package com.example.searchMicroService.controller;

import com.example.searchMicroService.DTO.ProductDTO;
import com.example.searchMicroService.DTO.ProductDtoWrapper;
import com.example.searchMicroService.entity.Product;
import com.example.searchMicroService.services.SolrServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SolrController {
    @Autowired
    SolrServiceImpl solrService;

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public Optional select(@RequestParam("querypara") String querypara) {
        return solrService.select(querypara);
    }

    @RequestMapping(value = "/searchByName",method = RequestMethod.GET)
    public ProductDtoWrapper search(@RequestParam("querypara") String querypara) {
        List<Product> products=solrService.findByCustomQuery(querypara);
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for(Product product: products){
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productDTOS.add(productDTO);
        }
        ProductDtoWrapper productDtoWrapper = new ProductDtoWrapper();
        productDtoWrapper.setProductList(productDTOS);
        return productDtoWrapper;
    }
    @RequestMapping(value = "/getByCategory",method = RequestMethod.GET)
    public ProductDtoWrapper getByCategory(@RequestParam("querypara") String querypara) {
        List<Product> products=solrService.findByNameOrCategory(querypara,querypara);
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for(Product product: products){
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productDTOS.add(productDTO);
        }
        ProductDtoWrapper productDtoWrapper = new ProductDtoWrapper();
        productDtoWrapper.setProductList(productDTOS);
        return productDtoWrapper;
    }

    @RequestMapping(value= "/add",method = RequestMethod.POST)
    public ProductDTO add(@RequestBody ProductDTO productDTO) {
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        Product createdProduct=solrService.save(product);
        ProductDTO createdProductDTO=new ProductDTO();
        BeanUtils.copyProperties(createdProduct,createdProductDTO);
        return createdProductDTO;
    }
    @RequestMapping(value= "/delete",method = RequestMethod.DELETE)
    public void delete(@RequestBody ProductDTO productDTO) {
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        solrService.delete(product);
        ProductDTO createdProductDTO=new ProductDTO();
    }
    @RequestMapping(value= "/update",method = RequestMethod.PUT)
    public ProductDTO update(@RequestBody ProductDTO productDTO) {
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        Product createdProduct=solrService.update(product);
        ProductDTO createdProductDTO=new ProductDTO();
        BeanUtils.copyProperties(createdProduct,createdProductDTO);
        return createdProductDTO;
    }
    @RequestMapping(value= "/deleteId",method = RequestMethod.GET)
    public ProductDTO deleteById(@RequestParam("productId") String productId) {
        solrService.deleteById(productId);
        return  new ProductDTO();
    }
}
