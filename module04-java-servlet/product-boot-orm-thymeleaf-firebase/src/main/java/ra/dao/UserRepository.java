package ra.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.model.User;
import ra.utils.Enum.Role;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByEmailAndPasswordAndRole(String email, String password, Role role);

    public Optional<User> findByUsername(String userName);

    public Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> findUsersByKeyword(@Param("keyword") String keyword);
}
