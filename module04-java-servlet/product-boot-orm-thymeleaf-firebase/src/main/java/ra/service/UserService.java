package ra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ra.dao.UserRepository;
import ra.dto.AddUserDto;
import ra.dto.EditUserDto;
import ra.dto.UserLoginDto;
import ra.model.Category;
import ra.model.Product;
import ra.model.User;
import ra.utils.Enum.CustomException;
import ra.utils.Enum.EmailExistsException;
import ra.utils.Enum.Role;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Search
    public List<User> searchByKeyword(String keyword) {
        return userRepository.findUsersByKeyword(keyword);
    }

    //add
    public User addUser(AddUserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailExistsException(userDto.getEmail());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStatus(true);
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    //Edit
    public User editUser(EditUserDto userDto) throws Exception {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new Exception("User not found with id: " + userDto.getId()));

        // Chuyển đổi từ EditUserDto sang User
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setStatus(userDto.getStatus());

        // Kiểm tra và cập nhật mật khẩu chỉ khi có sự thay đổi
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword());
        }
        return userRepository.save(user);
    }

    //Delete
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //Block / unblock
    public void userStatus(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(!user.getStatus());
        userRepository.save(user);
    }

    //check login
    public boolean checkLogin(UserLoginDto userLoginDto) {
        Optional<User> userOpt = userRepository.findByEmailAndRole(userLoginDto.getEmail(), userLoginDto.getRole());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Kiểm tra trạng thái status và mật khẩu
            if (user.getStatus() && user.getPassword().equals(userLoginDto.getPassword())) {
                return true;
            } else if (!user.getStatus()) {
                // Nếu user bị khóa (status = false)
                throw new CustomException("Account is blocked");
            }
        }
        // Nếu không tìm thấy user hoặc mật khẩu không khớp
        return false;
    }



}
