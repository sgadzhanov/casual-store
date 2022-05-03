package com.project.casualstore.model.service;

import com.project.casualstore.model.entity.BrandEntity;

public class ShoeAddServiceModel {
    private Long id;
    private Long brandId;
    private String name;
    private String description;
    private BrandEntity brand;
    private String imageUrl;


    public Long getId() {
        return id;
    }

    public ShoeAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public ShoeAddServiceModel setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ShoeAddServiceModel setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}