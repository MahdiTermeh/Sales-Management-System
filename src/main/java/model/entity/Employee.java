package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@SuperBuilder
@Data
public class Employee {
    public int id;
    public String name;
    public String family;
    public LocalDate birthDate;
    public String address;

}


//create table employees(
//id int primary key auto_increment,
//name varchar(20) not null,
//family varchar(20) not null,
//birth_date date not null,
//address varchar(40) not null);