package com.online.seva.repositories.jpa;

import com.online.seva.domain.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!mongodb"})
public interface UserRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
