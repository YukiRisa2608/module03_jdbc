package ra.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.Product;
import ra.model.User;
import ra.utils.Enum.Role;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByEmailAndPasswordAndRole(String email, String password, Role role);
// kiểm tra tồn lại
    public Optional<User> findByUsername(String userName);

    public Optional<User> findByEmail(String email);

    // Order by
    List<Product> findAllByOrderById();
    List<Product> findAllByOrderByUsername();
}
