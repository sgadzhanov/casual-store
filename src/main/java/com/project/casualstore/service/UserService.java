package com.project.casualstore.service;

import com.project.casualstore.model.service.UserRegisterServiceModel;

public interface UserService {
    void initUsersAndRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
}
