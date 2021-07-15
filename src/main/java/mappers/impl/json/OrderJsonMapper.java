package mappers.impl.json;

import mappers.Mapper;
import model.Category;
import model.Order;
import model.ShopItem;
import model.User;
import utils.GsonUtils;

import java.util.stream.Collectors;

public class OrderJsonMapper implements Mapper<Order, String> {

    @Override
    public Order toTarget(String source) {
        return (Order) GsonUtils.deserialize(source, Order.class);
    }

    @Override
    public String toSource(Order target) {
        var itemsOld = target.getItems();
        var userOld = target.getUser();
        if(userOld!=null){
            var userNew = new User();
            userNew.setId(userOld.getId());
            target.setUser(userNew);
        }
        if (itemsOld != null && !itemsOld.isEmpty()) {
            target.setItems(itemsOld.stream().map(itemOld -> {
                        var itemNew = new ShopItem();
                        itemNew.setId(itemOld.getId());
                        return itemNew;
                    }
            ).collect(Collectors.toList()));
        }
        String result = GsonUtils.serialize(target);
        target.setItems(itemsOld);
        target.setUser(userOld);
        return result;
    }
}
