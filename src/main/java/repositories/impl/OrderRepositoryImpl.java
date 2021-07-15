package repositories.impl;

import model.Order;
import repositories.OrderRepository;
import storages.OrderDataStorage;

public class OrderRepositoryImpl extends AbstractRepositoryImpl<Order>
    implements OrderRepository {

    public OrderRepositoryImpl(OrderDataStorage orderDataStorage){
        super(orderDataStorage);
    }

}
