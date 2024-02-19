package ra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Boolean status;
}

