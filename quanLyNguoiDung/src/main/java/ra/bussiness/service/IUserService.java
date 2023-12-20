package ra.bussiness.service;

import ra.bussiness.model.User;
import ra.bussiness.model.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();
    User findById(Long id);
    void add(User user);
    void update(User user);
    void delete(Long id);
    int countUserByRoleId(Integer roleId);
}