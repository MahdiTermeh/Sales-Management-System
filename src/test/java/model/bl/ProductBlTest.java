package model.bl;

import model.entity.Product;

;

public class ProductBlTest {
    public static void productBlSaveTest() throws Exception{
        Product product= Product.builder()
                .name("Dönner")
                .brand("Berlin Döner")
                .price(1)
                .build();
        ProductBl productBl=new ProductBl();
        System.out.println(productBl.save(product));

    }
    public static void productBlUpdateTest() throws Exception{
        Product product= Product.builder()
                .id(2)
                .name("Dönner")
                .brand("Royal Döner")
                .price(1)
                .build();
        ProductBl productBl=new ProductBl();
        System.out.println(productBl.update(product));
    }
    public static void productBlDeleteTest() throws Exception{
        ProductBl productBl=new ProductBl();
        System.out.println(productBl.delete(2));

    }
    public static void productBlFindAllTest() throws Exception{
        ProductBl productBl=new ProductBl();
        System.out.println(productBl.findAll());
    }
    public static void productBlFindByIdTest() throws Exception{
        ProductBl productBl=new ProductBl();
        System.out.println(productBl.findById(3));
    }

    public static void main(String[] args) throws Exception {
//        tested successfully
        productBlSaveTest();
//        tested successfully
//        productBlUpdateTest();
//        tested successfully
//        productBlDeleteTest();

//        tested successfully
//        productBlFindAllTest();
//        tested successfully
//        productBlFindByIdTest();
    }
}
