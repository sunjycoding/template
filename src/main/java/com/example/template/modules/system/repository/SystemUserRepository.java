package com.example.template.modules.system.repository;

import com.example.template.modules.system.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author created by sunjy on 12/21/23
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, String> {

    Optional<SystemUser> findUserByUsername(String username);

}