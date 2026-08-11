package controller;

import lombok.extern.slf4j.Slf4j;
import model.bl.EmployeeBl;
import model.entity.Employee;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeController {
    public Employee save (String name, String family, LocalDate birthDate, String address){
        log.debug("EmployeeController.save({},{})",name,family);
        try{
            Employee employee=Employee.builder()
                    .name(name)
                    .family(family)
                    .birthDate(birthDate)
                    .address(address)
                    .build();
            EmployeeBl employeeBl=new EmployeeBl();
            employee=employeeBl.save(employee);

            log.info("Employee saved successfully:"+employee);
            return employee;


        } catch (Exception e){
            log.error("Employee Save error:"+e.getMessage());
            return null;
        }


    }
    public void update(int id,String name, String family, LocalDate birthDate, String address){
        log.debug("EmployeeController.update({})",id);
        try{
            Employee employee=Employee.builder()
                    .id(id)
                    .name(name)
                    .family(family)
                    .birthDate(birthDate)
                    .address(address)
                    .build();
            EmployeeBl employeeBl=new EmployeeBl();
            if (employeeBl.update(employee)){
                log.info("Employee updated successfully:"+employee);

            }else{
                log.warn("There are no fields to update");
            }


        } catch (Exception e){
            log.error("Employee update Error: "+e.getMessage());
        }




    }
    public void delete(int employeeId) {
        log.debug("EmployeeController.delete({})",employeeId);
        try{
            EmployeeBl employeeBl=new EmployeeBl();
            if(employeeBl.delete(employeeId)){
                log.info("Employee deleted successfully: "+employeeId);

            } else {
                log.warn("There are no fields to delete");
            }
        } catch (Exception e){
            log.error("Employee delete error: "+ e.getMessage());

        }
    }
    public List<Employee> findAll(){
        log.debug("EmployeeContoller.findAll");
        try{
            List<Employee> employeeList=new ArrayList<>();
            EmployeeBl employeeBl=new EmployeeBl();
            employeeList=employeeBl.findAll();
            log.info("Employee findAll()");
            return employeeList;
        }catch (Exception e){
            log.error("Employee findAll Error:"+e.getMessage());
            return null;
        }
    }
    public Employee findById(int employeeId){
        log.debug("EmployeeContoller.findById({})",employeeId);
        try{
            EmployeeBl employeeBl=new EmployeeBl();
            Employee employee= employeeBl.findById(employeeId);
            log.info("Employee found successfully: "+employee);
            return employee;

        }catch (Exception e){
            log.error("Employee findById Error: "+e.getMessage());
            return null;

        }
    }


}
