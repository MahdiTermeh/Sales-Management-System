package controller;


import model.entity.Employee;

import java.time.LocalDate;

public class EmployeeControllerTest {
    public static void employeeControllerSaveTest(){
        EmployeeController employeeController=new EmployeeController();
//        employeeController.save("Adrien","Bale",87,"London,uk");
        Employee employee=employeeController.save("Adrien","Bale", LocalDate.of(1995,9,3),"London,uk");
        System.out.println("Employee is:"+employee);
    }
    public static void employeeControllerUpdateTest(){
        EmployeeController employeeController=new EmployeeController();
        employeeController.update(4,"Max","Schneider",LocalDate.of(1997,10,3),"Hesse,Germany");
    }
    public static void employeeControllerDeleteTest(){
        EmployeeController employeeController=new EmployeeController();
        employeeController.delete(6);
    }
    public static void employeeControllerFindAllTest(){
        EmployeeController employeeController=new EmployeeController();
        System.out.println(employeeController.findAll());
    }
    public static void employeeControllerFindByIdTest(){
        EmployeeController employeeController=new EmployeeController();
        employeeController. findById(4);
    }

    public static void main(String[] args) {
//        tested Successfully
        employeeControllerSaveTest();
//        tested Successfully
//        employeeControllerUpdateTest();
//        tested Successfully
//        employeeControllerDeleteTest();
//        tested Successfully
//        employeeControllerFindAllTest();
//        tested Successfully
//        employeeControllerFindByIdTest();
    }
}
