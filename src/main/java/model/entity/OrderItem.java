package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class OrderItem {
    public Order order;
    public Product product;
    public int quantity;
    public double price;
}

//create table order_items(
//invoice_number int not null,
//product_number int not null ,
//quantity int not null,
//price double not null,
//constraint fk_invoice_number
//foreign key (invoice_number)
//references orders(id),
//constraint fk_product_number
//foreign key(product_number)
//references products(id)
//);