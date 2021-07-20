package com.artemsilantev.app;

import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.services.CategoryService;
import com.artemsilantev.core.services.OrderService;
import com.artemsilantev.core.services.ProductService;
import com.artemsilantev.core.services.ShopItemService;
import com.artemsilantev.core.services.ShopService;
import com.artemsilantev.core.services.UserService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Test {

  @Autowired
  private CategoryService categoryService;
  @Autowired
  private ProductService productService;
  @Autowired
  private UserService userService;
  @Autowired
  private ShopItemService shopItemService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private OrderService orderService;

  public void run() {
    log.debug("test class run");
    categoryService.getAll().forEach(this::displayInfo);
    var category1 = new Category("Electronic device", "some description", null);
    var category2 = new Category("Smartphone", "some description about smartphones", category1);
    categoryService.create(category1);
    categoryService.create(category2);
    log.debug(
        "Root categories: " + StringUtils.join(categoryService.getRootCategories()));
    log.debug(
        "Children categories: " + StringUtils
            .join(categoryService.getChildrenCategories(category1.getId())));
    categoryService.save();

    productService.getAll().forEach(this::displayInfo);
    var product1 = new Product("Iphone", Arrays.asList(category2));
    var product2 = new Product("Electronic device", Arrays.asList(category1, category2));
    productService.create(product1);
    productService.create(product2);
    productService.save();

    shopService.getAll().forEach(this::displayInfo);
    var shop1 = new Shop("Shop1", "shop1 address", "shop1@gmail.com");
    var shop2 = new Shop("Shop2", "shop2 address", "shop2@mail.ru");
    shopService.create(shop1);
    shopService.create(shop2);
    shopService.save();

    shopItemService.getAll().forEach(this::displayInfo);
    var shopItem1 = new ShopItem(shop1, product1, 0.1, 10);
    var shopItem2 = new ShopItem(shop2, product2, 0.2, 20);
    shopItemService.create(shopItem1);
    shopItemService.create(shopItem2);
    shopItemService.save();

    userService.getAll().forEach(this::displayInfo);
    var user1 = new User("user1first", "user1second", "1", "user1@gmail.com",
        "user1 address");
    var user2 = new User("user2first", "user2second", "2", "user2@mail.ru",
        "user2 address");
    userService.create(user1);
    userService.create(user2);
    userService.save();

    orderService.getAll().forEach(this::displayInfo);
    var order1 = new Order(user1, Arrays.asList(shopItem1), 1.00);
    var order2 = new Order(user2, Arrays.asList(shopItem1, shopItem2), 2.00);
    orderService.create(order1);
    orderService.create(order2);
    orderService.save();
    log.debug("test class stop working");
  }

  private void displayInfo(Object obj) {

    log.debug("{}", obj.toString());
  }
}
