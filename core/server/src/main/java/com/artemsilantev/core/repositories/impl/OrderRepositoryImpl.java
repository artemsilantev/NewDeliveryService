package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repositories.OrderRepository;
import com.artemsilantev.core.storages.OrderDataStorage;

public class OrderRepositoryImpl extends AbstractRepositoryImpl<Order>
    implements OrderRepository {

    public OrderRepositoryImpl(OrderDataStorage orderDataStorage){
        super(orderDataStorage);
    }

}
