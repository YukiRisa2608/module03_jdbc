package ra.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.User;
import ra.utils.Enum.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;

    private String username;

    private String email;

    private Role role;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
