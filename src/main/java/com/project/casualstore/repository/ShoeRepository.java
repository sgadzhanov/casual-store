package com.project.casualstore.repository;

import com.project.casualstore.model.entity.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeEntity, Long> {

}