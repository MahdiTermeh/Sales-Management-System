package model.da;

import lombok.extern.slf4j.Slf4j;
import model.entity.DataAccess;
import model.entity.Product;
import model.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductDa implements DataAccess<Product,Integer>,AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ConnectionProvider connectionProvider=new ConnectionProvider();

    @Override
    public Product save(Product product) throws Exception {
        log.debug("ProductDa.save({})",product);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "insert into products(name,brand,price) values (?,?,?)"
                , PreparedStatement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getBrand());
        preparedStatement.setDouble(3,product.getPrice());

        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            int generatedKey=resultSet.getInt(1);
            product.setId(generatedKey);

        }
        log.info("Product saved successfully");
        return product;

    }

    @Override
    public boolean update(Product product) throws Exception {
        log.debug("ProductDa.update({})",product);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "update products set name=?,brand=?,price=? where id=?"
        );
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2, product.getBrand());
        preparedStatement.setDouble(3,product.getPrice());
        preparedStatement.setInt(4,product.getId());

        return preparedStatement.executeUpdate()>0;

    }

    @Override
    public boolean delete(Integer productId) throws Exception {
        log.debug("ProductDa.delete({})",productId);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "delete from products where id=?"
        );
        preparedStatement.setInt(1,productId);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public List<Product> findAll() throws Exception {
        log.debug("ProductDa.findAll()");
        List<Product> productList=new ArrayList<>();

        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from products order by name,brand"
        );

        preparedStatement.executeQuery();
        ResultSet resultSet=preparedStatement.getResultSet();

        while (resultSet.next()){
            Product product=Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .brand(resultSet.getString("brand"))
                    .price(resultSet.getDouble("price"))
                    .build();

            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product findById(Integer productId) throws Exception {
        log.debug("ProductDa.findById({})",productId);
        connection=connectionProvider.getConnection();
        preparedStatement=connection.prepareStatement(
                "select * from products where id=?"
        );
        preparedStatement.setInt(1,productId);
        preparedStatement.executeQuery();

        ResultSet resultSet=preparedStatement.getResultSet();
        if(resultSet.next()){
            Product product=Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .brand(resultSet.getString("brand"))
                    .price(resultSet.getDouble("price"))
                    .build();
//            log.info("product found successfully");
            return product;
        }
        else {
            throw new Exception("product could not be found");
        }
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Connection closed");

    }
}
