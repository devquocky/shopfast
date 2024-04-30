package personal.shopfast.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.shopfast.dao.entity.ERole;
import personal.shopfast.dao.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole eRole);
}
