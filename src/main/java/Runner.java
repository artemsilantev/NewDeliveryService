import contexts.CategoryContext;
import contexts.ProductContext;
import controllers.CategoryController;
import controllers.ProductController;
import model.Category;
import model.Product;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {


        CategoryController categoryController = CategoryContext.categoryController;
        categoryController.getAll().forEach(System.out::println);
        Category category1 = new Category("Electronic device");
        Category category2 = new Category("Electron");
        categoryController.add(category1);
        categoryController.add(category2);
        categoryController.save();

        ProductController productController = ProductContext.productController;
        productController.getAll().forEach(System.out::println);
        Product product1 = new Product("SmartPhone", Set.of(category1));
        Product product2 = new Product("Smart", Set.of(category2));
        Product product3 = new Product("SmartTV", Set.of(category1, category2));
        productController.add(product1);
        productController.add(product2);
        productController.add(product3);
        productController.save();;


    }
}
