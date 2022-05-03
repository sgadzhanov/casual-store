package com.project.casualstore.service.impl;

import com.project.casualstore.model.entity.UserEntity;
import com.project.casualstore.model.entity.UserRoleEntity;
import com.project.casualstore.model.entity.enums.UserRoleEnum;
import com.project.casualstore.model.service.UserRegisterServiceModel;
import com.project.casualstore.repository.UserRepository;
import com.project.casualstore.repository.UserRoleRepository;
import com.project.casualstore.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CasualtyUserServiceImpl casualtyUserService;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, CasualtyUserServiceImpl casualtyUserService, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.casualtyUserService = casualtyUserService;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initUsersAndRoles() {
        initRoles();
        initUsers();
    }

    private void initUsers() {
        if (this.userRepository.count() != 0) {
            return;
        }

        UserRoleEntity adminRole = this.userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = this.userRoleRepository.findByUserRole(UserRoleEnum.USER);

        UserEntity admin = new UserEntity();
        admin
                .setUsername("admin")
                .setPassword(this.passwordEncoder.encode("test"))
                .setEmail("admin@casualty.com")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setRoles(Set.of(adminRole, userRole));

        UserEntity stamat = new UserEntity();
        stamat
                .setUsername("stamat")
                .setPassword(this.passwordEncoder.encode("test"))
                .setEmail("stamat@hotmail.com")
                .setFirstName("Stamat")
                .setLastName("Stamatov")
                .setRoles(Set.of(userRole));

        this.userRepository.saveAll(Arrays.asList(admin, stamat));
    }

    private void initRoles() {
        if (this.userRoleRepository.count() != 0) {
            return;
        }
        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setUserRole(UserRoleEnum.ADMIN);

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserRole(UserRoleEnum.USER);

        this.userRoleRepository.saveAll(List.of(adminRole, userRole));
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserRoleEntity userRole = this.userRoleRepository.findByUserRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();
            newUser
                    .setFirstName(userRegisterServiceModel.getFirstName())
                    .setLastName(userRegisterServiceModel.getLastName())
                    .setUsername(userRegisterServiceModel.getLastName())
                    .setEmail(userRegisterServiceModel.getEmail())
                    .setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                    .setRoles(Set.of(userRole));

            newUser = this.userRepository.save(newUser);

        UserDetails principal = this.casualtyUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, newUser.getPassword(), principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}