package de.uulm.llam.repository;

import de.uulm.llam.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Role entity.
 */
public interface RoleRepository extends JpaRepository<Role,Long>{

}
