package com.project.casualstore.web;

import com.project.casualstore.model.dto.ClothesDto;
import com.project.casualstore.model.service.ClothesAddServiceModel;
import com.project.casualstore.model.service.ClothesUpdateServiceModel;
import com.project.casualstore.model.view.BrandViewModel;
import com.project.casualstore.model.view.ClothesDetailsViewModel;
import com.project.casualstore.model.view.ClothesViewModel;
import com.project.casualstore.service.BrandService;
import com.project.casualstore.service.ClothesService;
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
@RequestMapping("/clothes")
public class ClothesController {
    private final ClothesService clothesService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public ClothesController(ClothesService clothesService, ModelMapper modelMapper, BrandService brandService) {
        this.clothesService = clothesService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allClothes(Model model) {
        List<ClothesViewModel> allClothes = this.clothesService.findAllClothes();
        model.addAttribute("clothes", allClothes);

        return "clothes";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/details/{id}")
    public String clothesDetails(@PathVariable Long id, Model model, Principal principal) {
        ClothesDetailsViewModel clothesDetailsViewModel = this.clothesService.findById(id, principal.getName());

        model.addAttribute("clothesDetails", clothesDetailsViewModel);

        return "clothes-details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addClothesPage(Model model) {
        List<BrandViewModel> allBrands = this.brandService.getAllBrands();

        if (!model.containsAttribute("clothesDto")) {
            model.addAttribute("clothesDto", new ClothesDto())
                    .addAttribute("allBrands", allBrands);
        }
        return "clothes-add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addClothes(@Valid ClothesDto clothesDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("clothesDto", clothesDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.clothesDto", bindingResult);
            redirectAttributes.addFlashAttribute("allBrands", this.brandService.getAllBrands());

            return "redirect:/clothes/add";
        }
        ClothesAddServiceModel clothesAddServiceModel = this.modelMapper.map(clothesDto, ClothesAddServiceModel.class);
        this.clothesService.addClothes(clothesAddServiceModel);

        return "redirect:/clothes/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editClothesPage(@PathVariable Long id, Model model,
                                  @AuthenticationPrincipal CasualtyUser casualtyUser) {

        ClothesDetailsViewModel clothesDetailsViewModel = this.clothesService.findById(id, casualtyUser.getUserIdentifier());

        ClothesDto clothesDto = this.modelMapper.map(clothesDetailsViewModel, ClothesDto.class);
        model.addAttribute("clothesDto", clothesDto);

        return "clothes-update";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/edit")
    public String editClothes(@PathVariable Long id, @Valid ClothesDto clothesDto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("clothesDto", clothesDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.clothesDto", bindingResult);

            return "redirect:/clothes/" + id + "/edit";
        }

        ClothesUpdateServiceModel clothesUpdateServiceModel = this.modelMapper.map(clothesDto, ClothesUpdateServiceModel.class);
        clothesUpdateServiceModel.setId(clothesDto.getId());

        this.clothesService.editClothes(clothesUpdateServiceModel);

        return "redirect:/clothes/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public String deleteClothes(@PathVariable Long id) {

        this.clothesService.deleteClothes(id);

        return "redirect:/clothes/all";
    }
}