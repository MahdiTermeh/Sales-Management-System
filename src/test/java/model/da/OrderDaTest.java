package model.da;


import model.bl.EmployeeBl;
import model.entity.Employee;
import model.entity.Order;

import java.time.LocalDate;

public class OrderDaTest {
    public static void orderDaSaveTest() throws Exception{
        Employee employee=Employee.builder()
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        EmployeeBl employeeBl=new EmployeeBl();
        employee=employeeBl.save(employee);
        Order order= Order.builder()
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();

        try(OrderDa orderDa=new OrderDa()){
            System.out.println(orderDa.save(order));
        }
    }
    public static void orderDaUpdateTest() throws Exception{
        Employee employee=Employee.builder()
                .id(19)
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        Order order= Order.builder()
                .id(13)
                .employee(employee)
                .build();

        try(OrderDa orderDa=new OrderDa()){
            System.out.println(orderDa.update(order));
        }
    }
    public static void orderDaDeleteTest() throws Exception{
        try(OrderDa orderDa=new OrderDa()){
            System.out.println(orderDa.delete(14));
        }

    }
    public static void orderDaFindAllTest() throws Exception{
        try(OrderDa orderDa=new OrderDa()){
            System.out.println(orderDa.findAll());
        }
    }

    public static void orderDaFindByIdTest() throws Exception{
        try(OrderDa orderDa=new OrderDa()){
            System.out.println(orderDa.findById(2));
        }
    }

    public static void main(String[] args) throws Exception {

        //tested successfully
//        orderDaSaveTest();

        //tested successfully
//        orderDaUpdateTest();

        //tested successfully
//        orderDaDeleteTest();


        //tested successfully
//        orderDaFindAllTest();

        //tested successfully
//        orderDaFindByIdTest();
    }
}
