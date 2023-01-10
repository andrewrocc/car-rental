package infrastructure.repository;

import com.sun.istack.NotNull;
import infrastructure.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(@NotNull Pageable pageable);

    @Query(value = "SELECT * FROM USER u WHERE u.EMAIL=:email", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM USER u WHERE u.EMAIL LIKE %:email%", nativeQuery = true)
    List<User> findAllByEmail(@Param("email") String email);
}
