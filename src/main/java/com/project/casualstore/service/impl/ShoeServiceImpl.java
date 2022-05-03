package com.project.casualstore.service.impl;

import com.project.casualstore.model.entity.BrandEntity;
import com.project.casualstore.model.entity.ShoeEntity;
import com.project.casualstore.model.entity.UserEntity;
import com.project.casualstore.model.entity.UserRoleEntity;
import com.project.casualstore.model.entity.enums.UserRoleEnum;
import com.project.casualstore.model.service.ShoeAddServiceModel;
import com.project.casualstore.model.service.ShoeUpdateServiceModel;
import com.project.casualstore.model.view.ShoeDetailsViewModel;
import com.project.casualstore.model.view.ShoeViewModel;
import com.project.casualstore.repository.BrandRepository;
import com.project.casualstore.repository.ShoeRepository;
import com.project.casualstore.repository.UserRepository;
import com.project.casualstore.service.ShoeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImpl implements ShoeService {
    private final ShoeRepository shoeRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public ShoeServiceImpl(ShoeRepository shoeRepository, BrandRepository brandRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.shoeRepository = shoeRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initShoes() {
        if (this.shoeRepository.count() != 0) {
            return;
        }

        ShoeEntity fredPerryShoes = new ShoeEntity();

        fredPerryShoes
                .setName("B722")
                .setBrand(this.brandRepository
                        .findByName("Fred Perry")
                        .orElseThrow(() -> new IllegalStateException("Couldn't find the shoe brand!")))
                .setImageUrl("https://d30l99xc13l2t1.cloudfront.net/media/widget/cache/W/W/935c950b18dab398a6421e659146c14b/media/wysiwyg/2020/Footwear/WW/WW-Tennis-Footwear.jpg")
                .setDescription("A chunky leather tennis shoe with a thick rubber sole and twin tipped cut-out detail." +
                        " Designed with contrast trims and our signature stitched sports panelling.");


        ShoeEntity lacosteShoes = new ShoeEntity();
        lacosteShoes
                .setName("Men's Sneakers")
                .setBrand(this.brandRepository
                        .findByName("Lacoste")
                        .orElseThrow(() -> new IllegalStateException("Couldn't find the shoe brand!")))
                .setImageUrl("https://imagena1.lacoste.com/dw/image/v2/AAUP_PRD/on/demandware.static/-/Sites-master/default/dwe959c38b/43SMA0066_AB3_01.jpg?imwidth=915&impolicy=product")
                .setDescription("Take a vintage runner, enhance it with modern thoughtful detailing and you get the impressive L-Spin Deluxe. ");

        ShoeEntity benShermanShoes = new ShoeEntity();
        benShermanShoes
                .setName("Alec Trainer")
                .setBrand(this.brandRepository.findByName("Ben Sherman")
                        .orElseThrow(() -> new IllegalStateException("Couldn't find the shoe brand!")))
                .setImageUrl("https://images.asos-media.com/products/ben-sherman-wide-fit-minimal-lace-up-trainers-in-white/200863416-4?$n_640w$&wid=513&fit=constrain")
                .setDescription("Featuring classic, minimalist styling the Alec trainer is a shoe for any outfit. " +
                        "With all-black styling, and a subtle stitched chevron, with our iconic Ben Sherman logo embossed at the heel.");

        ShoeEntity stoneIslandShoes = new ShoeEntity();
        stoneIslandShoes
                .setName("SI Vision 1990")
                .setBrand(this.brandRepository.findByName("Stone Island")
                        .orElseThrow(() -> new IllegalStateException("Couldn't find the shoe brand!")))
                .setImageUrl("https://cdn.yoox.biz/11/11957512kb_13_d.jpg")
                .setDescription("Tennis-style leather sneakers. Micro perforation at sides. Rubber sole. Suede stabilizer in a contrasting color. " +
                        "Stone Island patch on the tongue and Stone Island logo printed on the back edge in a contrasting color. Lace up. Made in Italy by Diemme.");

        this.shoeRepository.saveAll(Arrays.asList(fredPerryShoes, lacosteShoes, benShermanShoes, stoneIslandShoes));
    }

    @Override
    public List<ShoeViewModel> findAllShoes() {

        return this.shoeRepository
                .findAll()
                .stream()
                .map(shoeEntity -> {
                    ShoeViewModel shoeViewModel = this.modelMapper.map(shoeEntity, ShoeViewModel.class);
                    shoeViewModel.setBrand(shoeEntity.getBrand().getName());

                    return shoeViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public ShoeDetailsViewModel findById(Long id, String name) {

        return this.shoeRepository
                .findById(id)
                .map(shoeEntity -> {
                    ShoeDetailsViewModel shoeDetailsViewModel = this.modelMapper.map(shoeEntity, ShoeDetailsViewModel.class);
                    shoeDetailsViewModel
                            .setBrand(shoeEntity.getBrand().getName())
                            .setCanDelete(this.isAdmin(name));
                    return shoeDetailsViewModel;
                }).get();
    }

    @Override
    public void deleteShoes(Long id) {
        this.shoeRepository.deleteById(id);
    }

    @Override
    public void editShoes(ShoeUpdateServiceModel shoeUpdateServiceModel) {
        ShoeEntity shoeEntity = this.shoeRepository
                .findById(shoeUpdateServiceModel.getId())
                .orElseThrow(() -> new IllegalStateException("Can't update shoes! Shoes with id + "
                        + shoeUpdateServiceModel.getId() + " + not found!"));

        shoeEntity
                .setName(shoeUpdateServiceModel.getName())
                .setDescription(shoeUpdateServiceModel.getDescription())
                .setImageUrl(shoeUpdateServiceModel.getImageUrl());

        this.shoeRepository.save(shoeEntity);

    }

    @Override
    public void addShoes(ShoeAddServiceModel shoeAddServiceModel) {
        ShoeEntity shoeEntity = this.modelMapper.map(shoeAddServiceModel, ShoeEntity.class);
//        BrandEntity brand = this.brandRepository.findById(shoeAddServiceModel.getBrandId()).orElse(null);

//        shoeEntity.setBrand(brand);

        this.shoeRepository.save(shoeEntity);
    }

    private boolean isAdmin(String name) {
        UserEntity userEntity = this.userRepository
                .findByUsername(name)
                .orElse(null);

        if (userEntity == null) {
            return false;
        }

        return userEntity
                .getRoles()
                .stream()
                .map(UserRoleEntity::getUserRole)
                .anyMatch(r -> r == UserRoleEnum.ADMIN);
    }
}