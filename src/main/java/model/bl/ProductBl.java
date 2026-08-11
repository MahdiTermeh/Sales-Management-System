package model.bl;

import lombok.extern.slf4j.Slf4j;
import model.da.ProductDa;
import model.entity.DataAccess;
import model.entity.Product;

import java.util.List;

@Slf4j
public class ProductBl implements DataAccess<Product,Integer> {
    @Override
    public Product save(Product product) throws Exception {
        log.debug("ProductBl.save({})",product);
            if(product.getPrice()>=0){
                try(ProductDa productDa=new ProductDa()){
                    return productDa.save(product);
                }

            }
            else {
                throw new Exception("Product's price must be positive");
            }
    }

    @Override
    public boolean update(Product product) throws Exception {
        log.debug("ProductBl.update({})",product);
        if(product.getPrice()>=0){
            try(ProductDa productDa=new ProductDa()){
                return productDa.update(product);
            }
        }
        else {
            throw new Exception("Product's price must be positive");
        }
    }

    @Override
    public boolean delete(Integer productId) throws Exception {
        log.debug("ProductBl.delete({})",productId);
        try(ProductDa productDa=new ProductDa()){
             return productDa.delete(productId);
        }
    }

    @Override
    public List<Product> findAll() throws Exception {
        log.debug("ProductBl.findAll");
        try(ProductDa productDa= new ProductDa()){
            return productDa.findAll();
        }
    }

    @Override
    public Product findById(Integer productId) throws Exception {
        log.debug("ProductBl.findById({})",productId);
        try(ProductDa productDa=new ProductDa()){
            return productDa.findById(productId);
        }
    }
}
