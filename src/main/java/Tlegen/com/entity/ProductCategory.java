package Tlegen.com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//ManyToMany: ProductCategory <-> Product

@AllArgsConstructor
@Getter @Setter
@ToString
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

}
