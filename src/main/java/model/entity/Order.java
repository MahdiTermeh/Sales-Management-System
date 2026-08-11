package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@SuperBuilder
@Data
public class Order {
    public int id;
    public Employee employee;
    public LocalDate orderDate;
    public double totalAmount;
}

//create table orders(
//id int primary key auto_increment,
//employee_id int not null,
//order_date date not null,
//total_amount double not null,
//constraint fk_employee
//foreign key (employee_id)
//references employees(id)
//);
