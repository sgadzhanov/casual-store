package com.project.casualstore.service;

import com.project.casualstore.model.service.ClothesAddServiceModel;
import com.project.casualstore.model.service.ClothesUpdateServiceModel;
import com.project.casualstore.model.view.ClothesDetailsViewModel;
import com.project.casualstore.model.view.ClothesViewModel;

import java.util.List;

public interface ClothesService {
    void initClothes();

    List<ClothesViewModel> findAllClothes();

    ClothesDetailsViewModel findById(Long id, String name);

    void editClothes(ClothesUpdateServiceModel clothesUpdateServiceModel);

    void deleteClothes(Long id);

    void addClothes(ClothesAddServiceModel clothesAddServiceModel);
}