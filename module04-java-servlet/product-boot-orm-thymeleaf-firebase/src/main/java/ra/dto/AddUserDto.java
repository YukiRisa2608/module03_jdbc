package ra.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddUserDto {
    private String username;

    private String email;
    private String password;
    private String birthday;
    private MultipartFile imgUrl;
}
