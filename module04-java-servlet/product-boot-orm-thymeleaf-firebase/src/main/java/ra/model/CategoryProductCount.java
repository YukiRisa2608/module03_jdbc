package ra.model;

public class CategoryProductCount {
    private Long categoryId;
    private String categoryName;
    private Long productCount;

    public CategoryProductCount(Long categoryId, String categoryName, Long productCount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productCount = productCount;
    }

    // Getters
    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
