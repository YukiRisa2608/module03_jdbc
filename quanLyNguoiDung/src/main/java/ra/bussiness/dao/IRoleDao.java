package ra.bussiness.dao;

import ra.bussiness.model.Role;

import java.util.List;

public interface IRoleDao {
    List<Role> findAllRoles();
    Role findRoleById(int roleId);
}
