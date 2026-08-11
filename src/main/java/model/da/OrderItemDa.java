package model.da;

import lombok.extern.slf4j.Slf4j;
import model.entity.*;
import model.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderItemDa implements AutoCloseable {

    private ConnectionProvider connectionProvider=new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;


    public OrderItem save(OrderItem orderItem) throws Exception {
        log.debug("OrderItemDa.save({})",orderItem);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "insert into order_items(invoice_number,product_number,quantity,price) values (?,?,?,?)"
        );
        preparedStatement.setInt(1,orderItem.getOrder().id);
        preparedStatement.setInt(2,orderItem.getProduct().id);
        preparedStatement.setInt(3,orderItem.getQuantity());
        preparedStatement.setDouble(4,orderItem.getPrice());

        preparedStatement.execute();

        log.info("OrderItem saved successfully");
        return orderItem;

    }

    public boolean update(OrderItem orderItem) throws Exception {
        log.debug("OrderItemDa.update({})",orderItem);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "update order_items set quantity=?,price=? where invoice_number=? and product_number=?"
        );
        preparedStatement.setInt(1,orderItem.getQuantity());
        preparedStatement.setDouble(2,orderItem.getPrice());
        preparedStatement.setInt(3,orderItem.getOrder().id);
        preparedStatement.setInt(4,orderItem.getProduct().id);

        return preparedStatement.executeUpdate()>0;
    }

    public boolean delete(Integer invoiceNumber, Integer productNumber) throws Exception {
        log.debug("OrderItemDa.delete({},{})",invoiceNumber,productNumber);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "delete from order_items where invoice_number=? and product_number=?"
        );
        preparedStatement.setInt(1,invoiceNumber);
        preparedStatement.setInt(2,productNumber);
        return preparedStatement.executeUpdate()>0;
    }

    public List<OrderItem> findAll() throws Exception {
        log.debug("OrderItemDa.findAll()");
        List<OrderItem> orderItemList=new ArrayList<>();

        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from full_report"
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

            Product product=Product.builder()
                    .id(resultSet.getInt("product_id"))
                    .name(resultSet.getString("product_name"))
                    .brand(resultSet.getString("brand"))
                    .price(resultSet.getDouble("product_price"))
                    .build();
            Order order=Order.builder()
                    .id(resultSet.getInt("order_id"))
                    .employee(employee)
                    .orderDate((resultSet.getDate("order_date")==null)?null:resultSet.getDate("order_date").toLocalDate())
                    .totalAmount(resultSet.getDouble("total_amount"))
                    .build();

            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(resultSet.getInt("quantity"))
                    .price(resultSet.getDouble("order_item_price"))
                    .build();

            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    public OrderItem findByInvoiceNumberAndProductNumber(Integer invoiceNumber, Integer productNumber) throws Exception {
        log.debug("OrderItemDa.findByInvoiceNumberAndProductNumber({},{})",invoiceNumber,productNumber);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from full_report where Order_id=? and product_id=?"
        );
        preparedStatement.setInt(1,invoiceNumber);
        preparedStatement.setInt(2,productNumber);
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

            Product product=Product.builder()
                    .id(resultSet.getInt("product_id"))
                    .name(resultSet.getString("product_name"))
                    .brand(resultSet.getString("brand"))
                    .price(resultSet.getDouble("product_price"))
                    .build();
            Order order=Order.builder()
                    .id(resultSet.getInt("order_id"))
                    .employee(employee)
                    .orderDate((resultSet.getDate("order_date")==null)?null:resultSet.getDate("order_date").toLocalDate())
                    .totalAmount(resultSet.getDouble("total_amount"))
                    .build();

            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(resultSet.getInt("quantity"))
                    .price(resultSet.getDouble("order_item_price"))
                    .build();
            log.info("orderItem found successfully");
            return orderItem;
        }
        else {
            throw new Exception("orderItem could not be found");
        }
    }


    public List<OrderItem> findItemsByOrder(Order order) throws Exception{
        log.debug("OrderItemDa.findItemsByOrder({})",order);
        List<OrderItem> orderItemList=new ArrayList<>();
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from full_report where Order_id=?"
        );
        preparedStatement.setInt(1,order.getId());
        preparedStatement.executeQuery();

        ResultSet resultSet=preparedStatement.getResultSet();
        while (resultSet.next()){
            Product product=Product.builder()
                    .id(resultSet.getInt("product_id"))
                    .name(resultSet.getString("product_name"))
                    .brand(resultSet.getString("brand"))
                    .price(resultSet.getDouble("product_price"))
                    .build();

            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(resultSet.getInt("quantity"))
                    .price(resultSet.getDouble("order_item_price"))
                    .build();
            orderItemList.add(orderItem);
        }
        return orderItemList;

    }
    public double calculateOrderTotal(Order order) throws Exception{
        log.debug("OrderItemDa.calculateOrderTotal({})",order);
        connection=ConnectionProvider.getConnection();

        preparedStatement=connection.prepareStatement(
                "select sum(quantity*price) from order_items where invoice_number=?"
        );
        preparedStatement.setInt(1,order.getId());
        preparedStatement.executeQuery();

        ResultSet resultSet=preparedStatement.getResultSet();
        resultSet.next();
        return resultSet.getDouble(1);




    }
    public boolean finalizeOrder(Order order) throws Exception{
        log.debug("OrderItemDa.finalizeOrder({})",order);
        connection=connectionProvider.getConnection();
        double totalAmount=calculateOrderTotal(order);
        preparedStatement=connection.prepareStatement(
                "update orders set total_amount=? where id=?;"
        );
        preparedStatement.setDouble(1,totalAmount);
        preparedStatement.setInt(2,order.getId());

        order.setTotalAmount(totalAmount);


        return preparedStatement.executeUpdate()>0;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Connection closed");

    }
}

//create view full_report as
//select
//E.id as employee_id,
//E.name as employee_name,
//E.family as employee_family,
//E.birth_date,
//E.address,
//O.id as Order_id,
//O.order_date,
//O.total_amount,
//OI.quantity,
//OI.price as order_item_price,
//P.id as product_id,
//P.name as product_name,
//P.brand,
//P.price as product_price
//        from
//employees E join
//orders O on E.id = O.employee_id join
//order_items OI on  O.id=OI.invoice_number join
//products P on OI.product_number=P.id;