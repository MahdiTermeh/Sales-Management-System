package model.bl;

import model.entity.Employee;

import java.time.LocalDate;

public class EmployeeBlTest {
    public static EmployeeBl employeeBl=new EmployeeBl();

    public static void employeeBlSaveTest() throws Exception{
        Employee employee=Employee.builder()
                .name("Anne")
                .family("Anderson")
                .birthDate(LocalDate.of(2000,4,10))
                .address("London,uk")
                .build();
        EmployeeBl employeeBl=new EmployeeBl();
        System.out.println(employeeBl.save(employee));
    }
    public static void employeeBlUpdateTest() throws Exception{
        Employee employee=Employee.builder()
                .id(2)
                .name("Anne")
                .family("Anderson")
                .birthDate(LocalDate.of(1997,4,10))
                .address("London,uk")
                .build();

        EmployeeBl employeeBl=new EmployeeBl();
        employeeBl.update(employee);
    }
    public static void employeeBlDeleteTest() throws Exception{

        EmployeeBl employeeBl=new EmployeeBl();
    }
    public static void employeeBlFindAllTest() throws Exception{

        EmployeeBl employeeBl=new EmployeeBl();
        System.out.println(employeeBl.findAll());
    }
    public static void employeeBlFindByIdTest() throws Exception{
        EmployeeBl employeeBl=new EmployeeBl();
        System.out.println(employeeBl.findById(2));

    }

    public static void main(String[] args) throws Exception {
        employeeBlSaveTest();
//        employeeBlUpdateTest();
//        employeeBlDeleteTest();
//        employeeBlFindByIdTest();
//        employeeBlFindAllTest();


    }


}
