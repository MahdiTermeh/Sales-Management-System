package controller;


import model.bl.EmployeeBl;
import model.entity.Employee;
import model.entity.Order;

import java.time.LocalDate;

public class OrderControllerTest {
    public static void orderControllerSaveTest() throws Exception {
        OrderController orderController=new OrderController();
//        orderController.save("Adrien","Bale",87,"London,uk");
        Employee employee=Employee.builder()
                .name("Katy")
                .family("Jones")
                .birthDate(LocalDate.of(1995,11,3))
                .address("NewYork,us")
                .build();
        EmployeeController employeeController=new EmployeeController();
        employee=employeeController.save("Katy","jones",LocalDate.of(1995,10,3),"NewYork,us");
        if(employee!=null){
        Order order=orderController.save(employee);
            System.out.println("Order is:"+order);
        }
    }
    public static void orderControllerUpdateTest(){
        OrderController orderController=new OrderController();
        Employee employee=Employee.builder()
                .id(6)
                .name("Max")
                .family("Schneider")
                .birthDate(LocalDate.of(1997,10,3))
                .address("Hesse,Germany")
                .build();
        orderController.update(11,employee);
    }
    public static void orderControllerDeleteTest(){
        OrderController orderController=new OrderController();
        orderController.delete(2);
    }
    public static void orderControllerFindAllTest(){
        OrderController orderController=new OrderController();
        System.out.println(orderController.findAll());
    }
    public static void orderControllerFindByIdTest(){
        OrderController orderController=new OrderController();
        orderController. findById(4);
    }

    public static void main(String[] args) throws Exception {
//        tested Successfully
        orderControllerSaveTest();
//        tested Successfully
//        orderControllerUpdateTest();
//        tested Successfully
//        orderControllerDeleteTest();
//        tested Successfully
//        orderControllerFindAllTest();
//        tested Successfully
//        orderControllerFindByIdTest();
    }
}
