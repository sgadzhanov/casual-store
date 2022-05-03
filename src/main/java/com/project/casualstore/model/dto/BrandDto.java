package com.project.casualstore.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BrandDto {
    private Long id;
    @Size(min = 3, max = 20)
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotBlank
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public BrandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}