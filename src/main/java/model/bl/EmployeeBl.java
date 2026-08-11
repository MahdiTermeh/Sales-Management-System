package model.bl;

import lombok.extern.slf4j.Slf4j;
import model.da.EmployeeDa;
import model.entity.DataAccess;
import model.entity.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
public class EmployeeBl implements DataAccess<Employee,Integer> {
    @Override
    public Employee save(Employee employee) throws Exception {
        log.debug("EmployeeBl.save({})",employee);
        LocalDate birthDay=employee.birthDate;
        int age= Period.between(birthDay, LocalDate.now()).getYears();
        if(age>=20 && age<=30){
            try(EmployeeDa employeeDa=new EmployeeDa()){
                return employeeDa.save(employee);
            }
        }
        else {
            throw new Exception("Employee's Age is not valid");
        }


    }

    @Override
    public boolean update(Employee employee) throws Exception {
        log.debug("EmployeeBl.update({})",employee);
        LocalDate birthDay=employee.birthDate;
        int age= Period.between(birthDay, LocalDate.now()).getYears();
        if(age>=20 && age<=30){
            try(EmployeeDa employeeDa=new EmployeeDa()){
                return employeeDa.update(employee);
            }
        }
        else {
            throw new Exception("Employee's Age is not valid");
        }
    }

    @Override
    public boolean delete(Integer employeeId) throws Exception {
        log.debug("EmployeeBl.delete({})",employeeId);
        try(EmployeeDa employeeDa= new EmployeeDa()){
            return employeeDa.delete(employeeId);
        }

    }

    @Override
    public List<Employee> findAll() throws Exception {
        log.debug("EmployeeBl.findAll");
        try(EmployeeDa employeeDa= new EmployeeDa()){
            return employeeDa.findAll();
        }
    }

    @Override
    public Employee findById(Integer employeeId) throws Exception {

        log.debug("mployeeBl.findById({})",employeeId);
        try(EmployeeDa employeeDa= new EmployeeDa()){
            return employeeDa.findById(employeeId);
        }
    }
}
