package com.project.casualstore.model.view;

public class BrandViewModel {
    private String name;
    private String description;
    private String imageUrl;
    private Long id;

    public String getName() {
        return name;
    }

    public BrandViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BrandViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}