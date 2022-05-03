package com.project.casualstore.web;

import com.project.casualstore.model.dto.ShoeDto;
import com.project.casualstore.model.service.ShoeAddServiceModel;
import com.project.casualstore.model.service.ShoeUpdateServiceModel;
import com.project.casualstore.model.view.ShoeDetailsViewModel;
import com.project.casualstore.model.view.ShoeViewModel;
import com.project.casualstore.service.BrandService;
import com.project.casualstore.service.ShoeService;
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
@RequestMapping("/shoes")
public class ShoeController {
    private final ShoeService shoeService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public ShoeController(ShoeService shoeService, ModelMapper modelMapper, BrandService brandService) {
        this.shoeService = shoeService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allShoes(Model model) {
        List<ShoeViewModel> shoes = this.shoeService.findAllShoes();
        
        model.addAttribute("shoes", shoes);

        return "shoes";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addShoe(Model model) {
        model.addAttribute("ShoesDto", new ShoeDto())
                .addAttribute("shoeBrands", this.brandService.getAllBrands());

        return "shoes-add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addShoesConfirm(@Valid ShoeDto shoeDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ShoesDto", shoeDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ShoesDto", bindingResult);
            redirectAttributes.addFlashAttribute("shoeBrands", this.shoeService.findAllShoes());

            return "redirect:/shoes/add";
        }
        ShoeAddServiceModel shoeAddServiceModel = this.modelMapper.map(shoeDto, ShoeAddServiceModel.class);

        this.shoeService.addShoes(shoeAddServiceModel);

        return "redirect:/shoes/all";
    }

    @GetMapping("/details/{id}")
    public String shoesDetails(@PathVariable Long id, Model model, Principal principal) {
        ShoeDetailsViewModel shoeDetailsViewModel = this.shoeService.findById(id, principal.getName());

        model.addAttribute("shoeDetails", shoeDetailsViewModel);

        return "shoes-details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public String deleteShoes(@PathVariable Long id) {

        this.shoeService.deleteShoes(id);

        return "redirect:/shoes/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editShoesPage(@PathVariable Long id, Model model,
                                @AuthenticationPrincipal CasualtyUser casualtyUser) {

        ShoeDetailsViewModel shoeDetailsViewModel = this.shoeService.findById(id, casualtyUser.getUserIdentifier());

        ShoeDto shoeModel = this.modelMapper.map(shoeDetailsViewModel, ShoeDto.class);
        model.addAttribute("shoeModel", shoeModel);

        return "shoes-update";
    }

    @PreAuthorize("hasRole('admin')")
    @PatchMapping("/{id}/edit")
    public String editShoes(@PathVariable Long id,
                            @Valid ShoeDto shoeDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shoeDto", shoeDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDto", bindingResult);

            return "redirect:/shoes/" + id + "/edit";
        }

        ShoeUpdateServiceModel shoeUpdateServiceModel = this.modelMapper.map(shoeDto, ShoeUpdateServiceModel.class);
        shoeUpdateServiceModel.setId(shoeDto.getId());

        this.shoeService.editShoes(shoeUpdateServiceModel);

        return "redirect:/shoes/all";
    }
}