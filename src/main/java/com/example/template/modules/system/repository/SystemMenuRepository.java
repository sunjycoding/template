package com.example.template.modules.system.repository;

import com.example.template.modules.system.model.SystemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author created by sunjy on 12/21/23
 */
@Repository
public interface SystemMenuRepository extends JpaRepository<SystemMenu, String> {

    Optional<SystemMenu> findSystemMenuByName(String name);

}