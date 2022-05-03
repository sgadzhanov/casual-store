package com.project.casualstore.web;

import com.project.casualstore.model.dto.UserRegisterDto;
import com.project.casualstore.model.service.UserRegisterServiceModel;
import com.project.casualstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegisterController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserRegisterController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterDto userRegisterDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        boolean passwordNotMatching = !userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword());

        if (bindingResult.hasErrors() || !userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            redirectAttributes.addFlashAttribute("password_matching", passwordNotMatching);

            return "redirect:/users/register";
        }
        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterDto, UserRegisterServiceModel.class);

        this.userService.registerAndLoginUser(userRegisterServiceModel);


        return "redirect:/users/login";
    }

    @ModelAttribute("userRegisterDto")
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }
}