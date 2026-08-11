package model.da;


import model.entity.Product;

public class ProductDaTest {
    public static void productDaSaveTest() throws Exception{
        Product product= Product.builder()
                .name("Galaxy s8+")
                .brand("Samsung")
                .price(400)
                .build();


        try(ProductDa productDa=new ProductDa()){
            System.out.println(productDa.save(product));
        }
    }
    public static void productDaUpdateTest() throws Exception{
        Product product= Product.builder()
                .id(1)
                .name("Dönner")
                .brand("Dönner Brothers")
                .price(7)
                .build();

        try(ProductDa productDa=new ProductDa()){
            System.out.println(productDa.update(product));
        }
    }
    public static void productDaDeleteTest() throws Exception{
        try(ProductDa productDa=new ProductDa()){
            System.out.println(productDa.delete(8));
        }

    }
    public static void productDaFindAllTest() throws Exception{
        try(ProductDa productDa=new ProductDa()){
            System.out.println(productDa.findAll());
        }
    }

    public static void productDaFindByIdTest() throws Exception{
        try(ProductDa productDa=new ProductDa()){
            System.out.println(productDa.findById(1));
        }
    }

    public static void main(String[] args) throws Exception {

        //tested successfully
        productDaSaveTest();

        //tested successfully
//        productDaUpdateTest();

        //tested successfully
//        productDaDeleteTest();


        //tested successfully
//        productDaFindAllTest();

        //tested successfully
//        productDaFindByIdTest();
    }
}
