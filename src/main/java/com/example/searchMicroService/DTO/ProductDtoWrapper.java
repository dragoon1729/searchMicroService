package com.example.searchMicroService.DTO;

import com.example.searchMicroService.entity.Product;

import java.util.List;

public class ProductDtoWrapper {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ProductDtoWrapper{" +
                "productList=" + productList +
                '}';
    }
}
