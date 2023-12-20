package ra.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Sử dụng lombok
@NoArgsConstructor // constructor không tham số
@AllArgsConstructor // constructor full tham số
@Data // bao gồm getter, setter, tostring
public class UserDto {
    private Long user_id;
    private String fullName;
    private String phone;
    private String address;
    private String status;
    private int role_id;
    private String role_name;
}
