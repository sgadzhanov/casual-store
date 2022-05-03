package com.project.casualstore.service.impl;

import com.project.casualstore.model.entity.BrandEntity;
import com.project.casualstore.model.entity.UserEntity;
import com.project.casualstore.model.entity.UserRoleEntity;
import com.project.casualstore.model.entity.enums.UserRoleEnum;
import com.project.casualstore.model.service.BrandAddServiceModel;
import com.project.casualstore.model.service.BrandUpdateServiceModel;
import com.project.casualstore.model.view.BrandDetailsViewModel;
import com.project.casualstore.model.view.BrandViewModel;
import com.project.casualstore.repository.BrandRepository;
import com.project.casualstore.repository.UserRepository;
import com.project.casualstore.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void initBrands() {
        if (this.brandRepository.count() != 0) {
            return;
        }

        BrandEntity fredPerry = new BrandEntity();
        fredPerry
                .setName("Fred Perry")
                .setImageUrl("https://thumbs.dreamstime.com/b/fred-perry-logo-popular-clothing-brand-famous-luxury-vector-icon-zaporizhzhia-ukraine-may-222305679.jpg")
                .setDescription("Frederick John Perry (18 May 1909 – 2 February 1995) was a British tennis and table tennis player and former world No. 1 from England who won 10 Majors including eight Grand Slam tournaments and two Pro Slams single titles, as well as six Major doubles titles. Perry won three consecutive Wimbledon Championships from 1934 to 1936 and was World Amateur number one tennis player during those three years. Prior to Andy Murray in 2013, Perry was the last British player to win the men's Wimbledon championship, in 1936,[4] and the last British player to win a men's singles Grand Slam title, until Andy Murray won the 2012 US Open.");

        BrandEntity lacoste = new BrandEntity();
        lacoste
                .setName("Lacoste")
                .setImageUrl("https://p2.zoon.ru/preview/o1CoW57NV4OaWP6WBx8fVA/2400x1500x85/1/8/c/original_50b32165a0f3027038000025_50b323bf360f3.jpg")
                .setDescription(
                        "René Lacoste was not predestined for a sporting career. " +
                                "Yet aged 18, he made the crucial decision to focus entirely on his passion for tennis, " +
                                "training tirelessly to hone his strength, precision and concentration. " +
                                "Willing himself to be “as perfect as possible”. Never defeated, he transformed every loss into a win for his technique." +
                                " An approach that proved effective: he won seven major singles tournaments, " +
                                "and played on the French team who took the Davis Cup in 1927 and 1928."
                );

        BrandEntity benSherman = new BrandEntity();
        benSherman
                .setName("Ben Sherman")
                .setImageUrl("https://thegroom.co.za/sites/default/files/brands/Screenshot_20210308-085128_Gallery-removebg-preview.png")
                .setDescription(
                        "Ben Sherman was a legend in his own right. A man described as \"always embracing the new and the different\"" +
                                "—someone constantly searching for the best of things. " +
                                "He disliked regularity, preferring to search out things that were perceived as unavailable to him. " +
                                "He was a passionate businessman with an artist’s soul."
                );

        BrandEntity stoneIsland = new BrandEntity();
        stoneIsland
                .setName("Stone Island")
                .setImageUrl("https://i.pinimg.com/originals/68/9a/41/689a41bf38980ab667c4330543f4c637.jpg")
                .setDescription(
                        "Stone Island was born in 1982 in Ravarino, Italy, a small town in the Province of Modena. " +
                                "The story begins almost by chance from the creative mind of Massimo Osti, " +
                                "with the study of a special material: a two-sided and two-tone truck tarpaulin, " +
                                "so rigid and full-bodied that it had to undergo heavy stone wash procedures to tame the structure of the material. " +
                                "The result was surprising, a garment with a worn and highly appealing appearance. " +
                                "Seven jackets were made in that single canvas, named Tela Stella. " +
                                "The fabric recalls the waxed jackets corroded by the sea and by the sun."
                );

        this.brandRepository.saveAll(Arrays.asList(fredPerry, lacoste, benSherman, stoneIsland));
    }

    @Override
    public List<BrandViewModel> getAllBrands() {
        return this.brandRepository
                .findAll()
                .stream()
                .map(brandEntity -> this.modelMapper.map(brandEntity, BrandViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandDetailsViewModel findById(Long id, String name) {
        return this.brandRepository
                .findById(id)
                .map(o -> mapDetailsView(o, name))
                .get();
    }

    private BrandDetailsViewModel mapDetailsView(BrandEntity brandEntity, String name) {
        BrandDetailsViewModel brandDetailsViewModel = this.modelMapper.map(brandEntity, BrandDetailsViewModel.class);
        brandDetailsViewModel.setCanDelete(this.isAdmin(name));
        brandDetailsViewModel.setDescription(brandEntity.getDescription())
                .setName(brandEntity.getName())
                .setImageUrl(brandEntity.getImageUrl());

        return brandDetailsViewModel;
    }


    @Override
    public boolean isAdmin(String username) {
        UserEntity userEntity = this.userRepository
                .findByUsername(username)
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

    @Override
    public void deleteById(Long id) {
        this.brandRepository.deleteById(id);
    }

    @Override
    public void editBrand(BrandUpdateServiceModel brandUpdateServiceModel) {
        BrandEntity brandEntity = this.brandRepository
                .findById(brandUpdateServiceModel.getId())
                .orElseThrow(() -> new IllegalStateException("There is no brand with id " + brandUpdateServiceModel.getId()));

        brandEntity
                .setName(brandUpdateServiceModel.getName())
                .setDescription(brandUpdateServiceModel.getDescription())
                .setImageUrl(brandUpdateServiceModel.getImageUrl());

        brandRepository.save(brandEntity);

    }

    @Override
    public void addBrand(BrandAddServiceModel brandAddServiceModel) {
        BrandEntity brand = this.modelMapper.map(brandAddServiceModel, BrandEntity.class);
        this.brandRepository.save(brand);
    }
}