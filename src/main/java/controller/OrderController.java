package controller;

import lombok.extern.slf4j.Slf4j;
import model.bl.OrderBl;
import model.entity.Employee;
import model.entity.Order;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderController {
    public Order save (Employee employee){
        log.debug("OrderController.save({})",employee);
        try{
            Order order=Order.builder()
                    .employee(employee)
                    .build();
            OrderBl orderBl=new OrderBl();
            order=orderBl.save(order);

            log.info("Order saved successfully:"+order);
            return order;


        } catch (Exception e){
            log.error("Order Save error:"+e.getMessage());
            return null;
        }


    }
    public void update(int id, Employee employee){
        log.debug("OrderController.update({})",id);
        try{
            Order order=Order.builder()
                    .id(id)
                    .employee(employee)
                    .build();
            OrderBl orderBl=new OrderBl();
            if (orderBl.update(order)){
                log.info("Order updated successfully:"+order);

            }else{
                log.warn("There are no fields to update");
            }


        } catch (Exception e){
            log.error("Order update Error: "+e.getMessage());
        }




    }
    public void delete(int orderId) {
        log.debug("OrderController.delete({})",orderId);
        try{
            OrderBl orderBl=new OrderBl();
            if(orderBl.delete(orderId)){
                log.info("Order deleted successfully: "+orderId);

            } else {
                log.warn("There are no fields to delete");
            }
        } catch (Exception e){
            log.error("Order delete error: "+ e.getMessage());

        }
    }
    public List<Order> findAll(){
        log.debug("OrderContoller.findAll");
        try{
            List<Order> orderList=new ArrayList<>();
            OrderBl orderBl=new OrderBl();
            orderList=orderBl.findAll();
            log.info("Order findAll()");
            return orderList;
        }catch (Exception e){
            log.error("Order findAll Error:"+e.getMessage());
            return null;
        }
    }
    public Order findById(int orderId){
        log.debug("OrderContoller.findById({})",orderId);
        try{
            OrderBl orderBl=new OrderBl();
            Order order= orderBl.findById(orderId);
            log.info("Order found successfully: "+order);
            return order;

        }catch (Exception e){
            log.error("Order findById Error: "+e.getMessage());
            return null;

        }
    }


}
