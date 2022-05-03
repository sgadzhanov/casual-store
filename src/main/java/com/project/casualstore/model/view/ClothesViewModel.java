package com.project.casualstore.model.view;

public class ClothesViewModel {
    private Long id;
    private String name;
    private String brand;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ClothesViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothesViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ClothesViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothesViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}