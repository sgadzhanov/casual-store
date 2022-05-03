package com.project.casualstore.model.view;

public class ClothesDetailsViewModel {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public ClothesDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ClothesDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothesDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ClothesDetailsViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothesDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}