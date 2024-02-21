package ra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ra.model.Product;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String categoryName;

    private Boolean status;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

}
