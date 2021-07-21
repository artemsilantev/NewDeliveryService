package com.artemsilantev.app;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.dto.OrderDTO;
import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.dto.ShopDTO;
import com.artemsilantev.core.dto.ShopItemDTO;
import com.artemsilantev.core.dto.UserDTO;
import com.artemsilantev.core.exceptions.IllegalEntityException;
import com.artemsilantev.core.services.CategoryService;
import com.artemsilantev.core.services.OrderService;
import com.artemsilantev.core.services.ProductService;
import com.artemsilantev.core.services.ShopItemService;
import com.artemsilantev.core.services.ShopService;
import com.artemsilantev.core.services.UserService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    try {
      create();
    }catch (IllegalEntityException illegalEntityException){
      log.warn(illegalEntityException.getMessage());
    }
    displayAll();
    var category = categoryService.get(1L);
    categoryService.getChildren(category.getId()).forEach(this::displayInfo);
    category.setName("new name");
    categoryService.update(category);
    save();


    log.debug("test class stop working");
  }

  private void create() {
    var category1 = new CategoryDTO(null, "Electronic device",
        "some description about electronic device", null);
    var category2 = new CategoryDTO(null, "Smartphone", "some description about smartphones",
            category1.getId());
    category1 = categoryService.create(category1);
    category2 = categoryService.create(category2);

    var product1 = new ProductDTO(null, "Iphone", Arrays.asList(category2));
    var product2 = new ProductDTO(null, "Electronic device", Arrays.asList(category1, category2));
    product1 = productService.create(product1);
    product2 = productService.create(product2);

    var shop1 = new ShopDTO(null, "Shop1", "shop1 address", "shop1@gmail.com");
    var shop2 = new ShopDTO(null, "Shop2", "shop2 address", "shop2@mail.ru");
    shop1 = shopService.create(shop1);
    shop2 = shopService.create(shop2);
    var shopItem1 = new ShopItemDTO(null, shop1, product1, 0.1, 10);
    var shopItem2 = new ShopItemDTO(null, shop2, product2, 0.2, 20);
    shopItem1 = shopItemService.create(shopItem1);
    shopItem2 = shopItemService.create(shopItem2);
    var user1 = new UserDTO(null, "user1first", "user1second", "1",
        "user1@gmail.com", "user1 address");
    var user2 = new UserDTO(null, "user2first", "user2second", "2",
        "user2@mail.ru", "user2 address");
    user1 = userService.create(user1);
    user2 = userService.create(user2);
    var order1 = new OrderDTO(null, user1, Arrays.asList(shopItem1), 1.00);
    var order2 = new OrderDTO(null, user2, Arrays.asList(shopItem1, shopItem2), 2.00);
    order1 = orderService.create(order1);
    order2 = orderService.create(order2);
  }

  private void displayAll() {
    categoryService.getAll().forEach(this::displayInfo);
    productService.getAll().forEach(this::displayInfo);
    shopService.getAll().forEach(this::displayInfo);
    shopItemService.getAll().forEach(this::displayInfo);
    userService.getAll().forEach(this::displayInfo);
    orderService.getAll().forEach(this::displayInfo);
  }

  private void save() {
    categoryService.save();
    productService.save();
    shopService.save();
    shopItemService.save();
    userService.save();
    orderService.save();
  }

  private void displayInfo(Object obj) {
    log.info("{}", obj.toString());
  }
}
