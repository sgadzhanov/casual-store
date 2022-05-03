package com.project.casualstore.model.service;

public class BrandAddServiceModel {
    private String name;
    private String description;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public BrandAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}