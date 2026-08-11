package model.bl;

import lombok.extern.slf4j.Slf4j;
import model.da.OrderItemDa;
import model.entity.Order;
import model.entity.OrderItem;

import java.util.List;

@Slf4j
public class OrderItemBl  {

    public OrderItem save(OrderItem orderItem) throws Exception {
        log.debug("OrderItemBl.save({})", orderItem);
        if (orderItem.getQuantity() >= 0) {
            if (orderItem.getPrice() >= 0) {
                try (OrderItemDa orderItemDa = new OrderItemDa()) {
                    return orderItemDa.save(orderItem);
                }
            } else {
                throw new Exception("OrderItem's price must be positive");
            }

        } else {
            throw new Exception("OrderItem's quantity must be positive");
        }
    }

    public boolean update(OrderItem orderItem) throws Exception {
        log.debug("OrderItemBl.update({})",orderItem);
        if (orderItem.getQuantity() >= 0) {
            if (orderItem.getPrice() >= 0) {
                try (OrderItemDa orderItemDa = new OrderItemDa()) {
                    return orderItemDa.update(orderItem);
                }
            } else {
                throw new Exception("OrderItem's price must be positive");
            }

        } else {
            throw new Exception("OrderItem's quantity must be positive");
        }
    }

    public boolean delete(Integer invoiceNumber, Integer productNumber) throws Exception {
        log.debug("OrderItemBl.delete({},{})",invoiceNumber,productNumber);
        try(OrderItemDa orderItemDa=new OrderItemDa()){
            return orderItemDa.delete(invoiceNumber, productNumber);
        }
    }

    public List<OrderItem> findAll() throws Exception {
        log.debug("OrderItemBl.findAll");
        try(OrderItemDa orderItemDa= new OrderItemDa()){
            return orderItemDa.findAll();
        }
    }

    public OrderItem findByInvoiceNumberAndProductNumber(Integer invoiceNumber, Integer productNumber) throws Exception {
        log.debug("OrderItemBl.findByInvoiceNumberAndProductNumber({},{})",invoiceNumber,productNumber);
        try(OrderItemDa orderItemDa= new OrderItemDa()){
            return orderItemDa.findByInvoiceNumberAndProductNumber(invoiceNumber,productNumber);
        }
    }
    public List<OrderItem> findItemsByOrder(Order order) throws Exception {
        log.debug("OrderItemBl.findItemsByOrder({})", order);
        try (OrderItemDa orderItemDa = new OrderItemDa()) {
            return orderItemDa.findItemsByOrder(order);
        }
    }
    public double calculateOrderTotal(Order order) throws Exception{
        log.debug("OrderItemBl.calculateOrderTotal({})", order);
        try (OrderItemDa orderItemDa = new OrderItemDa()) {
            return orderItemDa.calculateOrderTotal(order);
        }

    }
    public boolean finalizeOrder(Order order) throws Exception{
        log.debug("OrderItemBl.finalizeOrder({})",order);
                try (OrderItemDa orderItemDa = new OrderItemDa()) {
                    return orderItemDa.finalizeOrder(order);
                }

    }
}
