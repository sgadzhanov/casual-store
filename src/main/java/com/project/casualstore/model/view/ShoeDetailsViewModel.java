package com.project.casualstore.model.view;

public class ShoeDetailsViewModel {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private boolean canDelete;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ShoeDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ShoeDetailsViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ShoeDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}