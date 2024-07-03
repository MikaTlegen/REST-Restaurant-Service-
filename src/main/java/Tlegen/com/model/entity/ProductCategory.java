package Tlegen.com.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//ManyToMany: ProductCategory <-> Product

@AllArgsConstructor
@Getter @Setter
public class ProductCategory {

    private Integer id;
    private String name;
    private String type;
    List<Product> products; // (ManyToMany)

    public ProductCategory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        product.addProductCategory(this);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", products=" + products +
                "}/n";
    }
}
