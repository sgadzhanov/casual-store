package com.project.casualstore.model.view;

public class BrandDetailsViewModel {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean canDelete;

    public Long getId() {
        return id;
    }

    public BrandDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public BrandDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}