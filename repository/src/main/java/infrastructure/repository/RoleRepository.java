package infrastructure.repository;

import infrastructure.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(@Param("name") String name);
}
