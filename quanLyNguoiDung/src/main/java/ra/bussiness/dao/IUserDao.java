package ra.bussiness.dao;

import ra.bussiness.model.User;
import ra.bussiness.model.UserDto;

public interface IUserDao extends IGenericDao <UserDto, User, Long>{
    int countUsersByRoleId(Integer roleId);
}
