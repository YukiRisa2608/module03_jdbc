package ra.bussiness.serviceIpl;

import ra.bussiness.dao.IUserDao;
import ra.bussiness.daoIpl.UserDaoIpl;
import ra.bussiness.model.User;
import ra.bussiness.model.UserDto;
import ra.bussiness.service.IUserService;

import java.util.List;

public class UserServiceIpl implements IUserService {
    private static final IUserDao userDao = new UserDaoIpl();

    @Override
    public List<UserDto> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public int countUserByRoleId(Integer roleId) {
        return userDao.countUsersByRoleId(roleId);
    }


}
