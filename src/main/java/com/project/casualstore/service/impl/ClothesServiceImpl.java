package com.project.casualstore.service.impl;

import com.project.casualstore.model.entity.BrandEntity;
import com.project.casualstore.model.entity.ClothesEntity;
import com.project.casualstore.model.service.ClothesAddServiceModel;
import com.project.casualstore.model.service.ClothesUpdateServiceModel;
import com.project.casualstore.model.view.ClothesDetailsViewModel;
import com.project.casualstore.model.view.ClothesViewModel;
import com.project.casualstore.repository.BrandRepository;
import com.project.casualstore.repository.ClothesRepository;
import com.project.casualstore.service.ClothesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepository clothesRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public ClothesServiceImpl(ClothesRepository clothesRepository, BrandRepository brandRepository, ModelMapper modelMapper) {
        this.clothesRepository = clothesRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initClothes() {

        if (this.clothesRepository.count() != 0) {
            return;
        }

        ClothesEntity stoneIslandJacket = new ClothesEntity();
        stoneIslandJacket
                .setBrand(this.brandRepository
                        .findByName("Stone Island")
                        .orElseThrow(() -> new IllegalStateException("Couldn't find brand for this jacket.")))
                .setName("RIPSTOP GORE-TEX")
                .setDescription("RIPSTOP GORE-TEX WITH PACLITE® PRODUCT TECHNOLOGY_PACKABLE: Three components: " +
                        "Packable Anorak and Vest, Dual Pouch Bag with neck strap.")
                .setImageUrl("https://cdn.yoox.biz/16/16076259ET_13_f.jpg");

        ClothesEntity lacosteShirt = new ClothesEntity();
        lacosteShirt
                .setBrand(this.brandRepository
                        .findByName("Lacoste").orElseThrow(() -> new IllegalStateException("Couldn't find brand for this shirt.")))
                .setName("Classic Fit Shirt")
                .setImageUrl("https://imagena1.lacoste.com/dw/image/v2/AAUP_PRD/on/demandware.static/-/Sites-master/default/dwdcc1c83e/L1212_S5H_24.jpg?imwidth=915&impolicy=product")
                .setDescription("A signature design from the Lacoste wardrobe, this L.12.12 Polo Shirt in cotton petit piqué combines comfort and elegance. ");

        ClothesEntity benShermanShirt = new ClothesEntity();

        benShermanShirt
                .setBrand(this.brandRepository
                        .findByName("Ben Sherman")
                        .orElseThrow(() -> new IllegalStateException("Couldn't find brand for this shirt.")))
                .setName("Check Shirt Anise Blue")
                .setImageUrl("https://cdn11.bigcommerce.com/s-axz3gp0dm3/images/stencil/500x659/products/3969/18200/MS232-Ben-Sherman-House-Check-Shirt-Anise-Blue-Folded__43650.1619796589.jpg?c=1")
                .setDescription("This statement retro 70's geo print shirt from Ben Sherman " +
                        "comes in a slim fit with long sleeves and features a classic point concealed button down collar. Made from 100% cotton.");

        this.clothesRepository.saveAll(Arrays.asList(stoneIslandJacket, lacosteShirt, benShermanShirt));
    }

    @Override
    public List<ClothesViewModel> findAllClothes() {
        List<ClothesViewModel> allClothes = this.clothesRepository
                .findAll()
                .stream()
                .map(clothesEntity -> {
                    ClothesViewModel clothesViewModel = this.modelMapper.map(clothesEntity, ClothesViewModel.class);
                    clothesViewModel.setBrand(clothesEntity.getBrand().getName());

                    return clothesViewModel;
                })
                .collect(Collectors.toList());

        return allClothes;
    }

    @Override
    public ClothesDetailsViewModel findById(Long id, String name) {

        ClothesDetailsViewModel clothesViewModel = this.clothesRepository
                .findById(id)
                .map(clothesEntity -> {
                    ClothesDetailsViewModel clothesDetailsViewModel = this.modelMapper.map(clothesEntity, ClothesDetailsViewModel.class);
                    clothesDetailsViewModel.setBrand(clothesEntity.getBrand().getName());

                    return clothesDetailsViewModel;
                })
                .get();

        return clothesViewModel;
    }

    @Override
    public void editClothes(ClothesUpdateServiceModel clothesUpdateServiceModel) {
        ClothesEntity clothesEntity = this.clothesRepository
                .findById(clothesUpdateServiceModel.getId())
                .orElseThrow(() -> new IllegalStateException("Clothes to edit not found."));

        clothesEntity
                .setName(clothesUpdateServiceModel.getName())
                .setImageUrl(clothesUpdateServiceModel.getImageUrl())
                .setDescription(clothesUpdateServiceModel.getDescription());

        this.clothesRepository.save(clothesEntity);
    }

    @Override
    public void deleteClothes(Long id) {
        this.clothesRepository.deleteById(id);
    }

    @Override
    public void addClothes(ClothesAddServiceModel clothesAddServiceModel) {
        ClothesEntity clothesEntity = this.modelMapper.map(clothesAddServiceModel, ClothesEntity.class);

        this.clothesRepository.save(clothesEntity);
    }
}