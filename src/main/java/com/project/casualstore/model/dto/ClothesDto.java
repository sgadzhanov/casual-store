package com.project.casualstore.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClothesDto {
    private Long id;
    private Long brandId;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Size(min = 5)
    private String description;
    @NotBlank
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ClothesDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public ClothesDto setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothesDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothesDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothesDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}