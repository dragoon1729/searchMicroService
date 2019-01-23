package com.example.searchMicroService.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "Myprod")
public class Product {

    @Id
    @Indexed(name = "productId", type = "string")
    private String id;
    @Indexed(name = "productId", type = "string")
    private String productId;
    @Indexed(name = "name", type = "text")
    private String name;
    @Indexed(name = "category", type = "text")
    private String category;
    @Indexed(name = "imageUrl", type = "string")
    private String imageUrl;
    @Indexed(name = "description", type = "string")
    private String description;
    @Indexed(name = "merchentId", type = "string")
    private String merchentId;
    @Indexed(name = "price", type = "double")
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerchentId() {
        return merchentId;
    }

    public void setMerchentId(String merchentId) {
        this.merchentId = merchentId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
