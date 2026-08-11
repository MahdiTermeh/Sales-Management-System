package controller;


import model.bl.EmployeeBl;
import model.da.OrderItemDa;
import model.entity.Employee;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.Product;

import java.time.LocalDate;

public class OrderItemControllerTest {
    public static void orderItemControllerSaveTest() throws Exception {
        Employee employee=Employee.builder()
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        Product product= Product.builder()
                .id(5)
                .name("Galaxy s8+")
                .brand("Samsung")
                .price(400)
                .build();
        Order order= Order.builder()
                .id(10)
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();

        OrderItemController orderItemController=new OrderItemController();
        OrderItem orderItem=orderItemController.save(order,product,1,1);
        System.out.println("orderItem is:"+orderItem);
    }
    public static void orderItemControllerUpdateTest(){
        OrderItemController orderItemController=new OrderItemController();
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
                .id(4)
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();

        orderItemController.update(order,product,1,1);
    }
    public static void orderItemControllerDeleteTest(){
        OrderItemController orderItemController=new OrderItemController();
        orderItemController.delete(10,9);
    }
    public static void orderItemControllerFindAllTest(){
        OrderItemController orderItemController=new OrderItemController();
        System.out.println(orderItemController.findAll());
    }
    public static void orderItemControllerFindByInvoiceNumberAndProductNumberTest(){
        OrderItemController orderItemController=new OrderItemController();
        orderItemController. findByInvoiceNumberAndProductNumber(10,10);
    }

    public static void finalizeOrderTest() throws Exception{
        Order order=Order.builder()
                .id(10)
                .build();
        OrderItemController orderItemController=new OrderItemController();
        orderItemController.finalizeOrder(order);
    }
    public static void calculateOrderTotalTest() throws Exception{
        Order order=Order.builder()
                .id(100)
                .build();
        OrderItemController orderItemController=new OrderItemController();
        System.out.println(orderItemController.calculateOrderTotal(order));
    }
    public static void findItemsByOrderTest() throws Exception{
        Order order=Order.builder()
                .id(100)
                .build();
        OrderItemController orderItemController=new OrderItemController();
        System.out.println(orderItemController.findItemsByOrder(order));

    }

    public static void main(String[] args) throws Exception {
//        tested Successfully
//        orderItemControllerSaveTest();
//        tested Successfully
//        orderItemControllerUpdateTest();
//        tested Successfully
//        orderItemControllerDeleteTest();
//        tested Successfully
//        orderItemControllerFindAllTest();
//        tested Successfully
//        orderItemControllerFindByInvoiceNumberAndProductNumberTest();

//        calculateOrderTotalTest();

//        finalizeOrderTest();

        findItemsByOrderTest();
    }
}
