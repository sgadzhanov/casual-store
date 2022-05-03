package com.project.casualstore.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ShoeDto {
    private Long id;
    private Long brandId;
    @NotBlank
    @Size(min = 5)
    private String name;
    @NotBlank
    @Size(min = 10)
    private String description;
    @NotBlank
    @Size(min = 10)
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ShoeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public ShoeDto setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }
}