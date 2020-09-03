package online.shop.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long usedId;

    public Order(List<Product> products, Long usedId) {
        this.products = products;
        this.usedId = usedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUsedId() {
        return usedId;
    }

    public void setUsedId(Long usedId) {
        this.usedId = usedId;
    }
}
