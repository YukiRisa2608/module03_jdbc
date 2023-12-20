package ra.bussiness.daoIpl;

import ra.bussiness.dao.IUserDao;
import ra.bussiness.model.User;
import ra.bussiness.model.UserDto;
import ra.bussiness.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIpl implements IUserDao {
    // Find All User
    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        try (Connection conn = ConnectDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL FindAllUserWithRole()}");
             ResultSet rs = call.executeQuery()) {
            while (rs.next()) {
                UserDto userDto = new UserDto();
                userDto.setUser_id(rs.getLong("user_id"));
                userDto.setFullName(rs.getString("full_name"));
                userDto.setPhone(rs.getString("phone"));
                userDto.setAddress(rs.getString("address"));
                userDto.setStatus(rs.getString("status"));
                userDto.setRole_id(rs.getInt("role_id"));
                userDto.setRole_name(rs.getString("role_name"));

                userDtoList.add(userDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDtoList;
    }

    //Find User by ID
    @Override
    public User findById(Long id) {
        User user = null;

        try (Connection conn = ConnectDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL GetUserById(?)}")) {

            call.setLong(1, id);
            ResultSet rs = call.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getLong("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getString("status"));
                user.setRole_id(rs.getInt("role_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // Creat/ update
    @Override
    public void save(User user) {
        try (Connection conn = ConnectDB.openConnection()) {
            CallableStatement call;

            if (user.getUser_id() == null) {
                // Chức năng thêm mới
                call = conn.prepareCall("{CALL InsertUser(?, ?, ?, ?, ?)}");
                call.setInt(1, user.getRole_id());
                call.setString(2, user.getFullName());
                call.setString(3, user.getPhone());
                call.setString(4, user.getAddress());
                call.setString(5, user.getStatus());
            } else {
                // Chức năng cập nhật
                call = conn.prepareCall("{CALL UpdateUser(?, ?, ?, ?, ?, ?)}");
                call.setLong(1, user.getUser_id());
                call.setString(2, user.getFullName());
                call.setString(3, user.getPhone());
                call.setString(4, user.getAddress());
                call.setString(5, user.getStatus());
                call.setInt(6, user.getRole_id());
            }

            call.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete
    @Override
    public void delete(Long id) {
        try (Connection conn = ConnectDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL DeleteUserById(?)}")) {

            call.setLong(1, id);
            call.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // thống kê số lượng người dùng theo quyền (Out parameter)
    @Override
    public int countUsersByRoleId(Integer roleId) {
        try (Connection conn = ConnectDB.openConnection()) {
            CallableStatement call = conn.prepareCall("{CALL CountUsersByRoleId(?, ?)}");
            call.setInt(1, roleId);
            call.registerOutParameter(2, Types.INTEGER);
            call.execute();

            // Lấy ra tham số out
            return call.getInt(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
