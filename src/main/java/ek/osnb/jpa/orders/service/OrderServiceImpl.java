package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderLineRepository orderLineRepository, OrderRepository orderRepository){
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders(OrderStatus status) {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    /** Ensure the ID is null for new entities, for ikke at overskrive eksisterende
     * eller lede efter noget som ikke eksisterer **/
    @Override
    public Order createOrder(Order order) {
        order.setId(null);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get();
            updatedOrder.setOrderDate(order.getOrderDate());
            updatedOrder.setStatus(order.getStatus());
            // Update other fields as necessary
            return orderRepository.save(updatedOrder);
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }
}
