package infrastructure.repository;

import infrastructure.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM ROLE r WHERE r.NAME LIKE %:name%", nativeQuery = true)
    Role findByName(@Param("name") String name);
}
