package ra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ra.dao.UserRepository;
import ra.dto.UserLoginDto;
import ra.model.Product;
import ra.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    // hiển thị allUser
    public List<User> FindAll() {
        return userRepository.findAll();
    }

    // check login
    public boolean checkLogin(UserLoginDto user) {
        return userRepository.existsByEmailAndPasswordAndRole(user.getEmail(), user.getPassword(), user.getRole());
    }
//   // hiển thị theo tên
//    public Optional<User> findByUsername(String userName) {
//        return userRepository.findByUsername(userName);
//    }
//    // hiển thị theo email
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
    // them use
    public User addUser(User user) {
    return userRepository.save(user);
}
    // Xóa uer
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    // exit user
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    // Tìm kiếm phẩm theo ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
