package Tlegen.com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// ManyToMany: Product <-> ProductCategory
// ManyToOne: Product -> OrderDetail

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Product {

    private int id;
    private String name;
    private int quantity;
    private boolean available;
    private double price;
    List<ProductCategory> productCategories;// ManyToMany

    private OrderDetail orderDetail;

    public Product(int id, String name, int quantity, boolean available, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.available = available;
        this.price = price;
        this.orderDetail = orderDetail;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", available=" + available +
                ", price=" + price +
                ", productCategories=" + productCategories +
                ", orderDetail=" + orderDetail +
                "}/n";
    }

    public void addProductCategory(ProductCategory productCategory) {
        productCategories.add(productCategory);
        productCategory.getProducts().add(this); // Добавляем текущий продукт в список продуктов категории
    }
}
