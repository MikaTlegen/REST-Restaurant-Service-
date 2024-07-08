package Tlegen.com.entity;

import lombok.*;

//ManyToMany: Product <-> Productcategory

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ProductProductcategory {
    private int productProductcategoryId;
    private int productId;
    private int categoryId;
}
