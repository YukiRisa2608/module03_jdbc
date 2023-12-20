package ra.bussiness.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



// dto data transfer object

@NoArgsConstructor // constructor không tham số
@AllArgsConstructor // constructor full tham số
@Data
public class Role {
    private int role_id;
    private String role_name;
}
