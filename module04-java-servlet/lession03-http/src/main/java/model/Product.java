package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // ko tham số
@AllArgsConstructor // tất cả tham số
@Getter
@Setter
public class Product {
    private Long id;
    private  String productName;
    private Double price;
    private String description;
    private Boolean status;
    private  Integer stock;
}
