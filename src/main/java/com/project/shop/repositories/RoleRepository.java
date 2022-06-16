package com.project.shop.repositories;

import com.project.shop.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByUserType(Role.UserTypes type);
}
