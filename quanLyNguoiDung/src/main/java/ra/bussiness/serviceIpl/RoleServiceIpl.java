package ra.bussiness.serviceIpl;

import ra.bussiness.dao.IRoleDao;
import ra.bussiness.dao.IUserDao;
import ra.bussiness.daoIpl.RoleDaoIpl;
import ra.bussiness.daoIpl.UserDaoIpl;
import ra.bussiness.model.Role;
import ra.bussiness.service.IRoleService;

import java.util.List;

public class RoleServiceIpl implements IRoleService {
    private static final IRoleDao roleDao = new RoleDaoIpl();

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public Role findRoleById(int roleId) {
        return roleDao.findRoleById(roleId);
    }
}
