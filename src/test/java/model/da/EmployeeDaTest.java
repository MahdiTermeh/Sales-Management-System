package model.da;

import model.entity.Employee;

import java.time.LocalDate;

public class EmployeeDaTest {
    public static void employeeDaSaveTest() throws Exception{
        Employee employee=Employee.builder()
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1988,4,10))
                .address("London,uk")
                .build();

        try(EmployeeDa employeeDa=new EmployeeDa()){
            System.out.println(employeeDa.save(employee));

        }
    }
    public static void employeeDaUpdateTest() throws Exception{
        Employee employee=Employee.builder()
                .id(4)
                .name("William")
                .family("Anderson")
                .birthDate(LocalDate.of(1985,4,10))
                .address("London,uk")
                .build();

        try(EmployeeDa employeeDa=new EmployeeDa()){
            System.out.println(employeeDa.update(employee));

        }
    }
    public static void employeeDaDeleteTest() throws Exception{

        try(EmployeeDa employeeDa=new EmployeeDa()){
            System.out.println(employeeDa.delete(7));

        }
    }
    public static void employeeDaFindAllTest() throws Exception{
        try(EmployeeDa employeeDa=new EmployeeDa()){
            System.out.println(employeeDa.findAll());

        }
    }
    public static void employeeDaFindByIDTest() throws Exception{
        try(EmployeeDa employeeDa=new EmployeeDa()){
            System.out.println(employeeDa.findById(5));

        }
    }

    public static void main(String[] args) throws Exception {
        employeeDaSaveTest();
//        employeeDaUpdateTest();
//        employeeDaDeleteTest();
//        employeeDaFindAllTest();
//        employeeDaFindByIDTest();

    }

}
