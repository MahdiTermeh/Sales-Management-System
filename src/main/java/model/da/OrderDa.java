package model.da;

import lombok.extern.slf4j.Slf4j;
import model.entity.*;
import model.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderDa implements DataAccess<Order,Integer>,AutoCloseable {
    
    private ConnectionProvider connectionProvider=new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public Order save(Order order) throws Exception {
        log.debug("OrderDa.save({})",order);
        connection=connectionProvider.getConnection();

        LocalDate orderDate=LocalDate.now();

        preparedStatement=connection.prepareStatement(
                "insert into orders(employee_id,order_date,total_amount) values (?,?,0)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setInt(1,order.getEmployee().id);
        preparedStatement.setDate(2, Date.valueOf(orderDate));
        preparedStatement.execute();
        ResultSet resultSet= preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            int generatedId=resultSet.getInt(1);
            order.setId(generatedId);
        }
        order.setTotalAmount(0);
        order.setOrderDate(orderDate);

        log.info("Order saved successfully");
        return order;

    }

    @Override
    public boolean update(Order order) throws Exception {
        log.debug("OrderDa.update({})",order);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "update orders set employee_id=? where id=?"
        );
        preparedStatement.setInt(1,order.getEmployee().id);
        preparedStatement.setInt(2,order.getId());

        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean delete(Integer orderId) throws Exception {
        log.debug("OrderDa.delete({})",orderId);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "delete from orders where id=?"
        );
        preparedStatement.setInt(1,orderId);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public List<Order> findAll() throws Exception {
        log.debug("OrderDa.findAll()");
        List<Order> orderList=new ArrayList<>();

        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from order_report"
        );

        preparedStatement.executeQuery();
        ResultSet resultSet=preparedStatement.getResultSet();

        while (resultSet.next()){
            Employee employee=Employee
                    .builder()
                    .id(resultSet.getInt("employee_id"))
                    .name(resultSet.getString("employee_name"))
                    .family(resultSet.getString("employee_family"))
                    .birthDate((resultSet.getDate("birth_date")==null)?null:resultSet.getDate("birth_date").toLocalDate())
                    .address(resultSet.getString("address"))
                    .build();
            Order order=Order.builder()
                    .id(resultSet.getInt("order_id"))
                    .employee(employee)
                    .orderDate((resultSet.getDate("order_date")==null)?null:resultSet.getDate("order_date").toLocalDate())
                    .totalAmount(resultSet.getDouble("total_amount"))
                    .build();

            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Order findById(Integer orderId) throws Exception {
        log.debug("OrderDa.findById({})",orderId);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from order_report where Order_id=?"
        );
        preparedStatement.setInt(1,orderId);
        preparedStatement.executeQuery();

        ResultSet resultSet=preparedStatement.getResultSet();
        if(resultSet.next()){
            Employee employee=Employee
                    .builder()
                    .id(resultSet.getInt("employee_id"))
                    .name(resultSet.getString("employee_name"))
                    .family(resultSet.getString("employee_family"))
                    .birthDate((resultSet.getDate("birth_date")==null)?null:resultSet.getDate("birth_date").toLocalDate())
                    .address(resultSet.getString("address"))
                    .build();

            Order order=Order.builder()
                    .id(resultSet.getInt("order_id"))
                    .employee(employee)
                    .orderDate((resultSet.getDate("order_date")==null)?null:resultSet.getDate("order_date").toLocalDate())
                    .totalAmount(resultSet.getDouble("total_amount"))
                    .build();
            log.info("order found successfully");
            return order;
        }
        else {
            throw new Exception("order could not be found");
        }
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Connection closed");

    }
}


//create view order_report as
//select
//E.id as employee_id,
//E.name as employee_name,
//E.family as employee_family,
//E.birth_date,
//E.address,
//O.id as Order_id,
//O.order_date,
//O.total_amount
//        from
//employees E join
//orders O on E.id = O.employee_id;
