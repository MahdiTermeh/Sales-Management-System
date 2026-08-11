package model.bl;

import model.entity.Employee;
import model.entity.Order;

import java.time.LocalDate;

public class OrderBlTest {
    public static void orderBlSaveTest() throws Exception{
         Employee employee= model.entity.Employee.builder()
                .name("Jude")
                .family("bale")
                .birthDate(LocalDate.of(1998,4,10))
                .address("Wellington,NeuZealand")
                .build();
        EmployeeBl employeeBl=new EmployeeBl();
        employee=employeeBl.save(employee);
        Order order= Order.builder()
                .employee(employee)
                .build();

        OrderBl orderBl=new OrderBl();
        System.out.println(orderBl.save(order));

    }
    public static void orderBlUpdateTest() throws Exception{
        Employee employee=Employee.builder()
                .id(24)
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1990,4,10))
                .address("London,uk")
                .build();
        Order order= Order.builder()
                .id(12)
                .employee(employee)
                .orderDate(LocalDate.of(2026,6,10))
                .totalAmount(1000)
                .build();

        OrderBl orderBl=new OrderBl();
            System.out.println(orderBl.update(order));

    }
    public static void orderBlDeleteTest() throws Exception{
        OrderBl orderBl=new OrderBl();
        System.out.println(orderBl.delete(13));

    }
    public static void orderBlFindAllTest() throws Exception{
        OrderBl orderBl=new OrderBl();
        System.out.println(orderBl.findAll());
    }
    public static void orderBlFindByIdTest() throws Exception{
        OrderBl orderBl=new OrderBl();
        System.out.println(orderBl.findById(1));
    }

    public static void main(String[] args) throws Exception {
//        tested successfully
        orderBlSaveTest();
//        tested successfully
//        orderBlUpdateTest();
//        tested successfully
//        orderBlDeleteTest();

//        tested successfully
//        orderBlFindAllTest();
//        tested successfully
//        orderBlFindByIdTest();
    }
}
