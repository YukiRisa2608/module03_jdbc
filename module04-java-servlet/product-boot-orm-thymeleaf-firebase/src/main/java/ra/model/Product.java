package ra.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank
    @Size(min = 3)
    private String productName;

    @DecimalMin(value = "0.01")
    private Double price;

    @NotBlank
    private String description;

    @Min(1)
    private Integer quantity;

    @NotBlank
    private String classification;

    @NotNull
    private Boolean status;

    @DateTimeFormat(pattern = "dd MM yyyy")
    @PastOrPresent
    private LocalDateTime lastUpdated;
}

