package services.impl;

import dto.OrderDTO;
import mappers.Mapper;
import model.Order;
import repositories.OrderRepository;
import services.OrderService;

public class OrderServiceImpl extends AbstractServiceImpl<OrderDTO, Order>
    implements OrderService {

  public OrderServiceImpl(Mapper<OrderDTO, Order> mapper, OrderRepository orderRepository) {
    super(mapper, orderRepository);
  }

}
