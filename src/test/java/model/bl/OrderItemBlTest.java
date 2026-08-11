package model.bl;

import model.da.OrderItemDa;
import model.entity.Employee;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.Product;

import java.time.LocalDate;

public class OrderItemBlTest {
    public static void orderItemBlSaveTest() throws Exception{
        Employee employee=Employee.builder()
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        Product product= Product.builder()
                .id(3)
                .name("Galaxy s8+")
                .brand("Samsung")
                .price(400)
                .build();
        Order order= Order.builder()
                .id(10)
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();

        OrderItem orderItem= OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(10)
                .price(1000)
                .build();

        OrderItemBl orderItemBl=new OrderItemBl();
        System.out.println(orderItemBl.save(orderItem));

    }
    public static void orderItemBlUpdateTest() throws Exception{
        Employee employee=Employee.builder()
                .id(19)
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1998,4,10))
                .address("London,uk")
                .build();
        Product product= Product.builder()
                .id(3)
                .name("Galaxy s8+")
                .brand("Samsung")
                .price(400)
                .build();
        Order order= Order.builder()
                .id(4)
                .employee(employee)
                .orderDate(LocalDate.of(2026,8,10))
                .build();
        OrderItem orderItem= OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(1)
                .price(-2000)
                .build();

        OrderItemBl orderItemBl=new OrderItemBl();
        System.out.println(orderItemBl.update(orderItem));

    }
    public static void orderItemBlDeleteTest() throws Exception{
        OrderItemBl orderItemBl=new OrderItemBl();
        System.out.println(orderItemBl.delete(5,5));

    }
    public static void orderItemBlFindAllTest() throws Exception{
        OrderItemBl orderItemBl=new OrderItemBl();
        System.out.println(orderItemBl.findAll());
    }
    public static void orderItemFindByInvoiceNumberAndProductNumberTest() throws Exception{
        OrderItemBl orderItemBl=new OrderItemBl();
        System.out.println(orderItemBl.findByInvoiceNumberAndProductNumber(2,6));
    }
    public static void finalizeOrderTest() throws Exception{

        OrderItemBl orderItemBl=new OrderItemBl();
        Order order=Order.builder()
                .id(2)
                .build();
        System.out.println(orderItemBl.finalizeOrder(order));

    }
    public static void calculateOrderTotalTest() throws Exception{
        OrderItemBl orderItemBl=new OrderItemBl();
        Order order=Order.builder()
                .id(2)
                .build();
        System.out.println(orderItemBl.calculateOrderTotal(order));
    }
    public static void findItemsByOrderTest() throws Exception{
        OrderItemBl orderItemBl=new OrderItemBl();
        Order order=Order.builder()
                .id(200)
                .build();
        System.out.println(orderItemBl.findItemsByOrder(order));
    }

    public static void main(String[] args) throws Exception {
//        tested successfully
//        orderItemBlSaveTest();
//        tested successfully
//        orderItemBlUpdateTest();
//        tested successfully
//        orderItemBlDeleteTest();

//        tested successfully
//        orderItemBlFindAllTest();
//        tested successfully
//        orderItemFindByInvoiceNumberAndProductNumberTest();

//        calculateOrderTotalTest();

//        finalizeOrderTest();

        findItemsByOrderTest();
    }
}
