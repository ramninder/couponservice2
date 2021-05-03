package com.ramninder.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramninder.springcloud.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
