package model.bl;

import lombok.extern.slf4j.Slf4j;
import model.da.OrderDa;
import model.da.OrderDa;
import model.entity.DataAccess;
import model.entity.Order;

import java.util.List;

@Slf4j
public class OrderBl implements DataAccess<Order,Integer> {

    @Override
    public Order save(Order order) throws Exception {
        log.debug("OrderBl.save({})",order);
            try(OrderDa orderDa=new OrderDa()){
                return orderDa.save(order);
            }

    }

    @Override
    public boolean update(Order order) throws Exception {
        log.debug("OrderBl.update({})",order);
        try(OrderDa orderDa=new OrderDa()){
            return orderDa.update(order);
        }
    }

    @Override
    public boolean delete(Integer orderId) throws Exception {
        log.debug("OrderBl.delete({})",orderId);
        try(OrderDa orderDa=new OrderDa()){
            return orderDa.delete(orderId);
        }
    }

    @Override
    public List<Order> findAll() throws Exception {
        log.debug("OrderBl.findAll");
        try(OrderDa orderDa= new OrderDa()){
            return orderDa.findAll();
        }
    }

    @Override
    public Order findById(Integer orderId) throws Exception {
        log.debug("OrderBl.findById");
        try(OrderDa orderDa= new OrderDa()){
            return orderDa.findById(orderId);
        }
    }
}
