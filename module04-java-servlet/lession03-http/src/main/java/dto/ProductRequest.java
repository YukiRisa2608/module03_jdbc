package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // ko tham số
@AllArgsConstructor // tất cả tham số
@Getter
@Setter
public class ProductRequest {

    private  String productName;
    private Double price;
    private String description;
    private  Integer stock;
}
