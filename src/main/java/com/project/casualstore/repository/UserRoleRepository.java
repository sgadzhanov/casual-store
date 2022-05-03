package com.project.casualstore.repository;

import com.project.casualstore.model.entity.UserRoleEntity;
import com.project.casualstore.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByUserRole(UserRoleEnum userRole);

}