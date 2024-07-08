package Tlegen.com.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


//OneToMany:  OrderDetail -> Product

@AllArgsConstructor
@ToString
@Getter @Setter
public class OrderDetail {

    private int id;
    private String orderStatus;
    private double totalAmount;
    private List<Product> products;//OneToMany



    public OrderDetail() {
        this.products = new ArrayList<>();
    }

    public OrderDetail(int id, String orderStatus, double totalAmount) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }


    public void addProduct(Product product) {
        products.add(product);
        product.setOrderDetail(this);
    }


}


