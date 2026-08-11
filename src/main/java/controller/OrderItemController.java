package controller;

import lombok.extern.slf4j.Slf4j;
import model.bl.OrderItemBl;
import model.entity.Employee;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.Product;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderItemController {
    public OrderItem save (Order order, Product product, int quantity, double price){
        log.debug("OrderItemController.save({},{})",order.id,product.id);
        try{
            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(quantity)
                    .price(price)
                    .build();
            OrderItemBl orderItemBl=new OrderItemBl();
            orderItem=orderItemBl.save(orderItem);
            log.info("OrderItem saved successfully:"+orderItem);
            return orderItem;


        } catch (Exception e){
            log.error("OrderItem Save error:"+e.getMessage());
            return null;
        }


    }
    public void update(Order order, Product product, int quantity, double price){
        log.debug("OrderItemController.update({},{})",order.id,product.id);
        try{
            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(quantity)
                    .price(price)
                    .build();
            OrderItemBl orderItemBl=new OrderItemBl();
            if (orderItemBl.update(orderItem)){
                log.info("OrderItem updated successfully:"+orderItem);

            }else{
                log.warn("There are no fields to update");
            }


        } catch (Exception e){
            log.error("OrderItem update Error: "+e.getMessage());
        }




    }
    public void delete(Integer invoiceNumber, Integer productNumber) {
        log.debug("OrderItemController.delete({},{})",invoiceNumber,productNumber);
        try{
            OrderItemBl orderItemBl=new OrderItemBl();
            if(orderItemBl.delete(invoiceNumber,productNumber)){
                log.info("OrderItem deleted successfully:{},{} ",invoiceNumber,productNumber);

            } else {
                log.warn("There are no fields to delete");
            }
        } catch (Exception e){
            log.error("OrderItem delete error: "+ e.getMessage());

        }
    }
    public List<OrderItem> findAll(){
        log.debug("OrderItemContoller.findAll");
        try{
            List<OrderItem> orderItemList=new ArrayList<>();
            OrderItemBl orderItemBl=new OrderItemBl();
            orderItemList=orderItemBl.findAll();
            log.info("OrderItem findAll()");
            return orderItemList;
        }catch (Exception e){
            log.error("OrderItem findAll Error:"+e.getMessage());
            return null;
        }
    }
    public OrderItem findByInvoiceNumberAndProductNumber(Integer invoiceNumber, Integer productNumber){
        log.debug("OrderItemContoller.findByInvoiceNumberAndProductNumber({},{})",invoiceNumber,productNumber);
        try{
            OrderItemBl orderItemBl=new OrderItemBl();
            OrderItem orderItem= orderItemBl.findByInvoiceNumberAndProductNumber(invoiceNumber,productNumber);
            log.info("OrderItem found successfully: "+orderItem);
            return orderItem;

        }catch (Exception e){
            log.error("OrderItem findByInvoiceNumberAndProductNumber Error: "+e.getMessage());
            return null;

        }
    }
    public List<OrderItem> findItemsByOrder(Order order) throws Exception{
        log.debug("OrderItemContoller.findItemsByOrder({})",order);
        try{
            List<OrderItem> orderItemList=new ArrayList<>();
            OrderItemBl orderItemBl=new OrderItemBl();
            orderItemList=orderItemBl.findItemsByOrder(order);
            return orderItemList;
        }catch (Exception e){
            log.error("OrderItem findItemsByOrder:"+e.getMessage());
            return null;
        }
    }
    public double calculateOrderTotal(Order order) throws Exception{
        log.debug("OrderItemController.calculateOrderTotal({})",order);
        try {
            OrderItemBl orderItemBl=new OrderItemBl();
            return orderItemBl.calculateOrderTotal(order);

        } catch (Exception e){
            log.error("OrderItem findItemsByOrder Error: "+e.getMessage());
            return 0;
        }

    }
    public void finalizeOrder(Order order) throws Exception{
        log.debug("OrderItemController.finalizeOrder({})",order);
        try{

            OrderItemBl orderItemBl=new OrderItemBl();
            if (orderItemBl.finalizeOrder(order)){
                log.info("The order has been finalized successfully:"+order);

            }else{
                log.warn("There is no order to finalize");
            }

        } catch (Exception e){
            log.error("OrderItem finalize Error: "+e.getMessage());
        }
    }


}
