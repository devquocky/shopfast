package personal.shopfast.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.shopfast.dao.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
