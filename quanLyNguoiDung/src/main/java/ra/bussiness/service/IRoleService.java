package ra.bussiness.service;

import ra.bussiness.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAllRoles();
    Role findRoleById(int roleId);
}
