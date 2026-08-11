package model.da;


import controller.EmployeeController;
import controller.OrderController;
import model.bl.EmployeeBl;
import model.entity.Employee;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.Product;

import java.time.LocalDate;

public class OrderItemDaTest {
    public static void orderItemDaSaveTest() throws Exception{
        Employee employee=Employee.builder()
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        Product product= Product.builder()
                .id(9)
                .name("Galaxy s8+")
                .brand("Samsung")
                .price(400)
                .build();
        Order order= Order.builder()
                .id(10)
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();

        EmployeeBl employeeBl=new EmployeeBl();
        employee=employeeBl.save(employee);
        OrderItem orderItem= OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(10)
                .price(1000)
                .build();

        try(OrderItemDa orderItemDa=new OrderItemDa()){
            System.out.println(orderItemDa.save(orderItem));
        }
    }
    public static void orderItemDaUpdateTest() throws Exception{
        Employee employee=Employee.builder()
                .id(19)
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        Product product= Product.builder()
                .id(10)
                .name("Galaxy s8+")
                .brand("Samsung")
                .price(400)
                .build();
        Order order= Order.builder()
                .id(2)
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();
        OrderItem orderItem= OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(1)
                .price(2000)
                .build();

        try(OrderItemDa orderItemDa=new OrderItemDa()){
            System.out.println(orderItemDa.update(orderItem));
        }
    }
    public static void orderItemDaDeleteTest() throws Exception{
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            System.out.println(orderItemDa.delete(2,3));
        }

    }
    public static void orderItemDaFindAllTest() throws Exception{
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            System.out.println(orderItemDa.findAll());
        }
    }

    public static void findByInvoiceNumberAndProductNumberTest() throws Exception{
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            System.out.println(orderItemDa.findByInvoiceNumberAndProductNumber(3,3));
        }
    }

    public static void finalizeOrderTest() throws Exception{
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            Order order=Order.builder()
                    .id(3)
                    .build();
            System.out.println(orderItemDa.finalizeOrder(order));
        }

    }
    public static void calculateOrderTotalTest() throws Exception{
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            Order order=Order.builder()
                    .id(100)
                    .build();
            System.out.println(orderItemDa.calculateOrderTotal(order));
        }
    }
    public static void findItemsByOrderTest() throws Exception{
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            Order order=Order.builder()
                    .id(4)
                    .build();
            System.out.println(orderItemDa.findItemsByOrder(order));
        }
    }

    public static void main(String[] args) throws Exception {

        //tested successfully
//        orderItemDaSaveTest();

        //tested successfully
//        orderItemDaUpdateTest();

        //tested successfully
//        orderItemDaDeleteTest();


        //tested successfully
//        orderItemDaFindAllTest();

        //tested successfully
//        findByInvoiceNumberAndProductNumberTest();

//        calculateOrderTotalTest();

//        finalizeOrderTest();

//        findItemsByOrderTest();
    }
}
