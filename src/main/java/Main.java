import controller.EmployeeController;
import controller.OrderController;
import controller.OrderItemController;
import controller.ProductController;
import model.entity.Employee;
import model.entity.Order;
import model.entity.Product;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        OrderItemController orderItemController = new OrderItemController();

        EmployeeController employeeController = new EmployeeController();
        ProductController productController = new ProductController();
        Employee employee = employeeController.save("Lucas", "Weber", LocalDate.of(1995, 11, 3), "Hamburg,Germany");
//      Employee employee=employeeController.save("Sophie","Wagner", LocalDate.of(1995,3,3),"Frankfurt,Germany");
        if (employee != null) {
            OrderController orderController = new OrderController();
            Order order = orderController.save(employee);
            System.out.println("Order is:" + order);


            Product product = productController.save("ThinkPadE14", "Lenovo", 849.0);
            orderItemController.save(order, product, 2, 800);

            Product product1 = productController.save("XPS13", "Dell", 999.0);
            orderItemController.save(order, product1, 3, 950);

            Product product2 = productController.save("MX Master 3S", "Logitech", 89.99);
            orderItemController.save(order, product2, 4, 89.99);

            orderItemController.finalizeOrder(order);

            System.out.print("Your Orders are:");
            System.out.println(orderItemController.findItemsByOrder(order));
            System.out.println("*".repeat(50));

            System.out.println("Total Price is:"+orderItemController.calculateOrderTotal(order));








        }

    }
}
