package com.project.casualstore.service;

import com.project.casualstore.model.service.BrandAddServiceModel;
import com.project.casualstore.model.service.BrandUpdateServiceModel;
import com.project.casualstore.model.view.BrandDetailsViewModel;
import com.project.casualstore.model.view.BrandViewModel;

import java.util.List;

public interface BrandService {
    void initBrands();

    List<BrandViewModel> getAllBrands();

    BrandDetailsViewModel findById(Long id, String name);

    void addBrand(BrandAddServiceModel brandAddServiceModel);

    boolean isAdmin(String username);

    void deleteById(Long id);

    void editBrand(BrandUpdateServiceModel brandUpdateServiceModel);
}
