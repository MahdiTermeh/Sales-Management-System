package controller;

import lombok.extern.slf4j.Slf4j;
import model.bl.ProductBl;
import model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductController {
    private ProductBl productBl=new ProductBl();

    public Product save(String name,String brand,double price){
        log.debug("ProductController.save({},{})",name,brand);
        try{
            Product product=Product.builder()
                    .name(name)
                    .brand(brand)
                    .price(price)
                    .build();
            product=productBl.save(product);
            log.info("Product saved successfully");
            return product;

        }catch (Exception e){
            log.error("Product save Error: "+e.getMessage());
            return null;
        }
    }
    public void update(int id,String name,String brand,double price){
        log.debug("ProductController.update({},{})",name,brand);
        try{
            Product product=Product.builder()
                    .id(id)
                    .name(name)
                    .brand(brand)
                    .price(price)
                    .build();

            if(productBl.update(product)){
                log.info("Product updated successfully");
            }else {
                log.warn("There are no fields to update");
            }

        }catch (Exception e){
            log.error("Product update Error: "+e.getMessage());
        }
    }
    public void delete(int productId){
        log.debug("ProductController.delete({})",productId);
        try{

            if(productBl.delete(productId)){
                log.info("Product deleted successfully");
            }else{
                log.warn("There are no fields to delete");
            }


        }catch (Exception e){
            log.error("Product delete Error: "+e.getMessage());
        }
    }
    public List<Product> findAll(){
        log.debug("ProductController.findAll()");
        try{
            List<Product> productList=new ArrayList<>();
            productList=productBl.findAll();
            log.info("List of products found:");
            return productList;


        }catch (Exception e){
            log.error("Product findAll Error: "+e.getMessage());
            return null;

        }
    }
    public Product findById(int productId){
        log.debug("ProductController.findById({})",productId);
        try{
            Product product=productBl.findById(productId);
            log.info("Product found successfully");
            return product;

        }catch (Exception e){
            log.error("Product found Error:"+e.getMessage());
            return null;

        }
    }

}
