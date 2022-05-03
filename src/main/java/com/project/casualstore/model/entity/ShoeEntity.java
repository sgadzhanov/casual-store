package com.project.casualstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHOES")
public class ShoeEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;
    @ManyToOne
    private BrandEntity brand;
    @Column(nullable = false)
    private String imageUrl;

    public String getName() {
        return name;
    }

    public ShoeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ShoeEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}