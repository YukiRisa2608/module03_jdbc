package ra.bussiness.daoIpl;

import ra.bussiness.dao.IRoleDao;
import ra.bussiness.model.Role;
import ra.bussiness.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoIpl implements IRoleDao {
    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            try (CallableStatement call = conn.prepareCall("{CALL GetAllRoles()}");
                 ResultSet rs = call.executeQuery()) {
                while (rs.next()) {
                    Role role = new Role();
                    role.setRole_id(rs.getInt("role_id"));
                    role.setRole_name(rs.getString("role_name"));
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return roles;
    }

    @Override
    public Role findRoleById(int roleId) {
        try (Connection conn = ConnectDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL FindRoleById(?)}")) {

            call.setInt(1, roleId);
            ResultSet rs = call.executeQuery();

            if (rs.next()) {
                Role role = new Role();
                role.setRole_id(rs.getInt("role_id"));
                role.setRole_name(rs.getString("role_name"));
                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Trả về null nếu không tìm thấy role
    }
}
