package ra.student.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private  Integer studentId;
    private String studentName;
    private String phoneNumber;
    private boolean sex;
    private Date birthday;
    private String imageUrl;
    private String address;
}
