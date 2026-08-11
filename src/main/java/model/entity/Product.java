package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class Product {
    public int id;
    public String name;
    public String brand;
    public double price;
}


//create table products(
//id int primary key auto_increment,
//name varchar(20) not null,
//brand varchar(20) not null,
//price double not null);
