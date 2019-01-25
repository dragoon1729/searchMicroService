package com.example.searchMicroService.controller;

import com.example.searchMicroService.DTO.ProductDTO;
import com.example.searchMicroService.DTO.ProductDtoWrapper;
import com.example.searchMicroService.entity.Product;
import com.example.searchMicroService.services.SolrServiceImpl;
import org.apache.commons.math3.analysis.function.Exp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        ProductDtoWrapper productDtoWrapper = new ProductDtoWrapper();
        try {
            List<Product> products = solrService.findByCustomQuery(querypara);

            HashMap<String,Product> uniqueProduct=new HashMap<>();
            List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
            for(Product product: products){

                if(uniqueProduct.containsKey(product.getProductId()) && uniqueProduct.get(product.getProductId()).getPrice()>product.getPrice())
                    uniqueProduct.put(product.getProductId(),product);

                else if(!uniqueProduct.containsKey(product.getProductId()))
                    uniqueProduct.put(product.getProductId(),product);


            }
            for (Product product:products)
            {
                if(uniqueProduct.get(product.getProductId())!=null)
                {

                    ProductDTO productDTO = new ProductDTO();

                    BeanUtils.copyProperties(uniqueProduct.get(product.getProductId()), productDTO);
                    uniqueProduct.remove(product.getProductId());
                    productDTOS.add(productDTO);
                }
            }
            Collections.sort(productDTOS, new Comparator() {

                @Override
                public int compare(Object o1, Object o2) {
                    ProductDTO s1 = (ProductDTO) o1;
                    ProductDTO s2 = (ProductDTO) o2;
                    return s1.getName().toLowerCase().indexOf(querypara.toLowerCase()) - s2.getName().toLowerCase().indexOf(querypara.toLowerCase());
                }
            });

            productDtoWrapper.setProductList(productDTOS);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return productDtoWrapper;
    }
    @RequestMapping(value = "/getByCategory",method = RequestMethod.GET)
    public ProductDtoWrapper getByCategory(@RequestParam("querypara") String querypara) {

        ProductDtoWrapper productDtoWrapper = new ProductDtoWrapper();
        try{
        List<Product> products=solrService.findByCategory(querypara,querypara);
        HashMap<String,Product> uniqueProduct=new HashMap<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products){

            if(uniqueProduct.containsKey(product.getProductId()) && uniqueProduct.get(product.getProductId()).getPrice()>product.getPrice())
                uniqueProduct.put(product.getProductId(),product);

            else if(!uniqueProduct.containsKey(product.getProductId()))
                uniqueProduct.put(product.getProductId(),product);


        }
        for (Product product:products)
        {
            if(uniqueProduct.get(product.getProductId())!=null)
            {

                ProductDTO productDTO = new ProductDTO();

                BeanUtils.copyProperties(uniqueProduct.get(product.getProductId()), productDTO);
                uniqueProduct.remove(product.getProductId());
                productDTOS.add(productDTO);
            }
        }
        productDtoWrapper.setProductList(productDTOS);}
        catch (Exception e)
        {
            System.out.println(e);
        }
        return productDtoWrapper;
    }

    @RequestMapping(value= "/add",method = RequestMethod.POST)
    public ProductDTO add(@RequestBody ProductDTO productDTO) {

        ProductDTO createdProductDTO=new ProductDTO();
        try{
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        Product createdProduct=solrService.save(product);
        BeanUtils.copyProperties(createdProduct,createdProductDTO);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return createdProductDTO;
    }
    @RequestMapping(value= "/delete",method = RequestMethod.DELETE)
    public void delete(@RequestBody ProductDTO productDTO) {
       try
        {Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        solrService.delete(product);}
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    @RequestMapping(value= "/update",method = RequestMethod.PUT)
    public ProductDTO update(@RequestBody ProductDTO productDTO) {

        ProductDTO createdProductDTO=new ProductDTO();
        try{
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        Product createdProduct=solrService.update(product);
        BeanUtils.copyProperties(createdProduct,createdProductDTO);}
        catch (Exception e)
        {
            System.out.println(e);
        }
        return createdProductDTO;
    }
    @RequestMapping(value= "/deleteId",method = RequestMethod.GET)
    public ProductDTO deleteById(@RequestParam("productId") String productId) {
        try {
            solrService.deleteById(productId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return  new ProductDTO();
    }
}
