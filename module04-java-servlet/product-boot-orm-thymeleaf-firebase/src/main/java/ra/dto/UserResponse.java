package ra.dto;

import static jakarta.persistence.TemporalType.TIMESTAMP;

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

    private String birthday;

    private Role role;

    private String lastUpdated;

    private String createdDate;

    public UserResponse(User user) {
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
