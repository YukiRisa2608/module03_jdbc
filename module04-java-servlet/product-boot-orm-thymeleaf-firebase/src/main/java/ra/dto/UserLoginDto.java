package ra.dto;


import lombok.*;
import ra.utils.Enum.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {
    private String email;
    private String password;
    private Role role = Role.ADMIN;
}
