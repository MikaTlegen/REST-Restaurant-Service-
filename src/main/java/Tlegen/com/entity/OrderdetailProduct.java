package Tlegen.com.entity;

//OneToMany: Orderdetail -> Product

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class OrderdetailProduct {
    private int orderdetailId;
    private int productId;
}
