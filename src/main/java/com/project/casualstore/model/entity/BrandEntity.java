package com.project.casualstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BRANDS")
public class BrandEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @OneToMany(mappedBy = "brand")
    private List<ClothesEntity> clothes = new ArrayList<>();
    @OneToMany(mappedBy = "brand")
    private List<ShoeEntity> shoes = new ArrayList<>();


    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<ClothesEntity> getClothes() {
        return clothes;
    }

    public BrandEntity setClothes(List<ClothesEntity> clothes) {
        this.clothes = clothes;
        return this;
    }

    public List<ShoeEntity> getShoes() {
        return shoes;
    }

    public BrandEntity setShoes(List<ShoeEntity> shoes) {
        this.shoes = shoes;
        return this;
    }
}