package online.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long userId;

    public Order(Long usedId) {
        this.userId = usedId;
        this.products = new ArrayList<>();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", products=" + products + ", userId=" + userId + '}';
    }
}
