package ra.present;

import ra.bussiness.model.Role;
import ra.bussiness.model.User;
import ra.bussiness.model.UserDto;
import ra.bussiness.service.IRoleService;
import ra.bussiness.service.IUserService;
import ra.bussiness.serviceIpl.RoleServiceIpl;
import ra.bussiness.serviceIpl.UserServiceIpl;
import ra.bussiness.util.InputMethods;

import java.util.List;

public class UserManager {
    private static final IUserService userService = new UserServiceIpl();
    private static final IRoleService roleService = new RoleServiceIpl();


    public static void main(String[] args) {
        while (true) {
            System.out.println("===Menu===");
            System.out.println("1. Display all users");
            System.out.println("2. Add new user");
            System.out.println("3. Update user by ID");
            System.out.println("4. Delete user by ID");
            System.out.println("5. Count users by role ID");
            System.out.println("6. Exit the program");

            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    displayAllUsers();
                    break;
                case 2:
                    addNewUser();
                    break;
                case 3:
                    //update
                    break;
                case 4:
                    deleteUserById();
                    break;
                case 5:
                    countUsersByRoleId();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid input");
                    break;
            }
        }
    }

    private static void addNewUser() {
        // Hiển thị danh sách các role để người dùng chọn
        System.out.println("Roles list:");
        displayAllRoles();

        // chọn role
        System.out.print("Enter Role ID to add new user: ");
        int roleId = InputMethods.getInteger();

        //lấy thông tin của role từ ID
        Role role = roleService.findRoleById(roleId);
        if (role == null) {
            System.err.println("Invalid Role ID");
            return;
        }

        // Nhập thông tin user
        System.out.print("Enter the Full Name: ");
        String fullName = InputMethods.getString();

        System.out.print("Enter the Phone: ");
        String phone = InputMethods.getString();

        System.out.print("Enter the Address: ");
        String address = InputMethods.getString();

        System.out.print("Enter the Status: ");
        String status = InputMethods.getString();

        // Tạo  đối tượng User mới
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setStatus(status);
        newUser.setRole_id(roleId);

        // Gọi phương thức thêm người dùng mới
        userService.add(newUser);
        System.out.println("New user added successfully.");
    }

    //display all roles
    private static void displayAllRoles() {
        List<Role> roles = roleService.findAllRoles();
        for (Role role : roles) {
            System.out.println(role.getRole_id() + ". " + role.getRole_name());
        }
    }

    //delete user by id
    private static void deleteUserById() {
        System.out.println("Enter the user ID to delete:");
        Long userId = InputMethods.getLong();
        userService.delete(userId);
        System.out.println("User with ID " + userId + "deleted successfully.");
    }


    // Count user by role id
    private static void countUsersByRoleId() {
        System.out.println("Enter the role ID to count users: ");
        int roleId = InputMethods.getInteger();
        int userCount = userService.countUserByRoleId(roleId);
        System.out.println("Number of users with role ID " + roleId + ": " + userCount);
    }

    // Display all User
    private static void displayAllUsers() {
        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            System.err.println("The user list is empty");
            return;
        }
        System.out.println("List users:");
        for (UserDto user : users) {
            System.out.println(user);
        }
    }


}
