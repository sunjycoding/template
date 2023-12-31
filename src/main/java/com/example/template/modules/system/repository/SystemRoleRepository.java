package com.example.template.modules.system.repository;

import com.example.template.modules.system.model.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author created by sunjy on 12/30/23
 */
@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, String> {

}