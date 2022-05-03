package com.project.casualstore.web;

import com.project.casualstore.model.dto.BrandDto;
import com.project.casualstore.model.service.BrandAddServiceModel;
import com.project.casualstore.model.service.BrandUpdateServiceModel;
import com.project.casualstore.model.view.BrandDetailsViewModel;
import com.project.casualstore.model.view.BrandViewModel;
import com.project.casualstore.service.BrandService;
import com.project.casualstore.service.impl.CasualtyUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allBrands(Model model) {
        List<BrandViewModel> allBrands = this.brandService.getAllBrands();

        model.addAttribute("brands", allBrands);
        return "brands";
    }

    @GetMapping("/details/{id}")
    public String brandDetails(@PathVariable Long id, Model model, Principal principal) {
        BrandDetailsViewModel brandDetails = this.brandService.findById(id, principal.getName());

        model.addAttribute("brandDetails", brandDetails);

        return "brand-details";
    }

    //    @PreAuthorize("isAdmin(#id)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addBrand(Model model) {
        model.addAttribute("BrandDto", new BrandDto());
        return "brand-add";
    }


    //    @PreAuthorize("isAdmin(#id)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addBrandConfirm(@Valid BrandDto brandDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("BrandDto", brandDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.BrandDto", bindingResult);

            return "redirect:/brands/add";
        }
        BrandAddServiceModel brandAddServiceModel = this.modelMapper.map(brandDto, BrandAddServiceModel.class);
        this.brandService.addBrand(brandAddServiceModel);

        return "redirect:/brands/all";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable Long id, Principal principal) {
        this.brandService.deleteById(id);

        return "redirect:/brands/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editBrand(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal CasualtyUser currentUser) {

        BrandDetailsViewModel brandDetailsViewModel = this.brandService.findById(id, currentUser.getUserIdentifier());
        BrandDto brandModel = this.modelMapper.map(brandDetailsViewModel, BrandDto.class);

        model.addAttribute("brandModel", brandModel);
        return "brand-update";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/edit")
    public String editBrandConfirm(@PathVariable Long id,
                                   @Valid BrandDto brandDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandDto", brandDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDto", bindingResult);

            return "redirect:/brands/" + id + "/edit";
        }
        BrandUpdateServiceModel brandUpdateServiceModel = this.modelMapper.map(brandDto, BrandUpdateServiceModel.class);

        brandUpdateServiceModel.setId(id);

        this.brandService.editBrand(brandUpdateServiceModel);

        return "redirect:/brands/all";
    }
}