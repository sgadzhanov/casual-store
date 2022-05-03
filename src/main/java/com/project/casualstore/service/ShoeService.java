package com.project.casualstore.service;

import com.project.casualstore.model.entity.ShoeEntity;
import com.project.casualstore.model.service.ShoeAddServiceModel;
import com.project.casualstore.model.service.ShoeUpdateServiceModel;
import com.project.casualstore.model.view.ShoeDetailsViewModel;
import com.project.casualstore.model.view.ShoeViewModel;

import java.util.List;

public interface ShoeService {
    void initShoes();

    List<ShoeViewModel> findAllShoes();

    ShoeDetailsViewModel findById(Long id, String name);

    void deleteShoes(Long id);

    void editShoes(ShoeUpdateServiceModel shoeUpdateServiceModel);

    void addShoes(ShoeAddServiceModel shoeAddServiceModel);
}