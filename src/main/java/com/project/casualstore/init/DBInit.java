package com.project.casualstore.init;

import com.project.casualstore.service.BrandService;
import com.project.casualstore.service.ClothesService;
import com.project.casualstore.service.ShoeService;
import com.project.casualstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final BrandService brandService;
    private final ShoeService shoeService;
    private final ClothesService clothesService;

    public DBInit(UserService userService, BrandService brandService, ShoeService shoeService, ClothesService clothesService) {
        this.userService = userService;
        this.brandService = brandService;
        this.shoeService = shoeService;
        this.clothesService = clothesService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.initUsersAndRoles();
        this.brandService.initBrands();
        this.shoeService.initShoes();
        this.clothesService.initClothes();
    }
}