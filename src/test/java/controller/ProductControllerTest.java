package controller;


import model.entity.Product;

public class ProductControllerTest {

    public static void productControllerSaveTest(){
        ProductController productController=new ProductController();
        Product product=productController.save("iPhone16","Apple",1700.0);
        System.out.println("Product is:"+product);
    }
    public static void productControllerUpdateTest(){
        ProductController productController=new ProductController();
        productController.update(5,"iPhone17","Apple",3000.0);
    }
    public static void productControllerDeleteTest(){
        ProductController productController=new ProductController();
        productController.delete(4);
    }
    public static void productControllerFindAllTest(){
        ProductController productController=new ProductController();
        System.out.println(productController.findAll());
    }
    public static void productControllerFindByIdTest(){
        ProductController productController=new ProductController();
        System.out.println(productController.findById(3));
    }

    public static void main(String[] args) {
//        tested successfully
//        productControllerFindByIdTest();
//        tested successfully
//        productControllerFindAllTest();
//        tested successfully
//        productControllerDeleteTest();
//        tested successfully
//        productControllerUpdateTest();
//        tested successfully
        productControllerSaveTest();

    }
}


