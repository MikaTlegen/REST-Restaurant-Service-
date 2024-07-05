package Tlegen.com.entity;

import lombok.*;

//ManyToMany: Product <-> Productcategory

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ProductProductcategory {
    private int product_id;
    private int productcategory_id;
}
