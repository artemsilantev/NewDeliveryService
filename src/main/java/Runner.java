import contexts.*;
import lombok.extern.slf4j.Slf4j;
import model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        log.info("start working");
        var categoryService = CategoryContext.CATEGORY_SERVICE;
        categoryService.getAll().forEach(Runner::displayInfo);
        var category1 = new Category("Electronic device", "some description", null);
        var category2 = new Category("Smartphone", "some description about smartphones", category1);
        categoryService.create(category1);
        categoryService.create(category2);
        log.debug("Category 1 parents: " + StringUtils.join(categoryService.getParents(category1.getId())));
        log.debug("Category 2 parents: " + StringUtils.join(categoryService.getParents(category2.getId())));
        categoryService.save();

        var productService = ProductContext.PRODUCT_SERVICE;
        productService.getAll().forEach(Runner::displayInfo);
        var product1 = new Product("Iphone", Arrays.asList(category2));
        var product2 = new Product("Electronic device", Arrays.asList(category1, category2));
        productService.create(product1);
        productService.create(product2);
        productService.save();

        var shopService = ShopContext.SHOP_SERVICE;
        shopService.getAll().forEach(Runner::displayInfo);
        var shop1 = new Shop("Shop1", "shop1 address", "shop1@gmail.com");
        var shop2 = new Shop("Shop2", "shop2 address", "shop2@mail.ru");
        shopService.create(shop1);
        shopService.create(shop2);
        shopService.save();

        var shopItemService = ShopItemContext.SHOP_ITEM_SERVICE;
        shopItemService.getAll().forEach(Runner::displayInfo);
        var shopItem1 = new ShopItem(shop1, product1, 0.1, 10);
        var shopItem2 = new ShopItem(shop2, product2, 0.2, 20);
        shopItemService.create(shopItem1);
        shopItemService.create(shopItem2);
        shopItemService.save();

        var userService = UserContext.USER_SERVICE;
        userService.getAll().forEach(Runner::displayInfo);
        var user1 = new User("user1first","user1second","1","user1@gmail.com",
                "user1 address");
        var user2 = new User("user2first","user2second","2","user2@mail.ru",
                "user2 address");
        userService.create(user1);
        userService.create(user2);
        userService.save();

        var orderService = OrderContext.ORDER_SERVICE;
        orderService.getAll().forEach(Runner::displayInfo);
        var order1 = new Order(user1, Arrays.asList(shopItem1), 1.00);
        var order2 = new Order(user2, Arrays.asList(shopItem1, shopItem2),2.00);
        orderService.create(order1);
        orderService.create(order2);
        orderService.save();

        log.info("stop working");
    }

    private static void displayInfo(Object obj){
        log.debug("{}", obj.toString());
    };
}
