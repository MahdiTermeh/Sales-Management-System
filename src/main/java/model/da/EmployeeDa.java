package model.da;

import lombok.extern.slf4j.Slf4j;
import model.entity.DataAccess;
import model.entity.Employee;
import model.utils.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class EmployeeDa implements DataAccess<Employee,Integer>,AutoCloseable {

    private ConnectionProvider connectionProvider=new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public Employee save(Employee employee) throws Exception {

        log.debug("EmployeeDa.save({})",employee);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "Insert into employees(name,family,birth_date,address) values (?,?,?,?)"
                , Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getFamily());
        preparedStatement.setDate(3, Date.valueOf(employee.getBirthDate()));
        preparedStatement.setString(4,employee.getAddress());

        preparedStatement.execute();
        ResultSet resultSet= preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            int generatedId=resultSet.getInt(1);
            employee.setId(generatedId);
        }
//        log.info("Employee saved");


        return employee;
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        log.debug("EmployeeDa.update({})",employee);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "update employees set name=?,family=?,birth_date=?,address=? where id=?"
        );

        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getFamily());
        preparedStatement.setDate(3, Date.valueOf(employee.getBirthDate()));
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setInt(5,employee.getId());



        return preparedStatement.executeUpdate()>0;

    }

    @Override
    public boolean delete(Integer employeeId) throws Exception {

        log.debug("EmployeeDa.delete({})",employeeId);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "delete from employees where id=?"
        );
        preparedStatement.setInt(1,employeeId);

        return preparedStatement.executeUpdate()>0;

    }

    @Override
    public List<Employee> findAll() throws Exception {
        log.debug("EmployeeDa.findAll()");
        List<Employee> employeeList=new ArrayList<Employee>();
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from employees order by family,name"
        );
        preparedStatement.executeQuery();
        ResultSet resultSet=preparedStatement.getResultSet();
        while(resultSet.next()){
            Employee employee=Employee
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .birthDate((resultSet.getDate("birth_date")==null)?null:resultSet.getDate("birth_date").toLocalDate())
                    .address(resultSet.getString("address"))
                    .build();
            employeeList.add(employee);

        }
//        log.info("List of Employees are: ");
        return employeeList;
    }

    @Override
    public Employee findById(Integer employeeId) throws Exception {
        log.debug("EmployeeDa.findById({})",employeeId);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from employees where id=?"
        );
        preparedStatement.setInt(1,employeeId);
        preparedStatement.executeQuery();
        ResultSet resultSet= preparedStatement.getResultSet();
        if(resultSet.next()){
            Employee employee=
                    Employee.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .birthDate((resultSet.getDate("birth_date")==null)?null:resultSet.getDate("birth_date").toLocalDate())
                            .address(resultSet.getString("address"))
                            .build();
//            log.info("Employee found successfully");
            return employee;
        }
        else {
            throw  new Exception("Employee could  not be found");

        }
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Connection closed");
    }
}
