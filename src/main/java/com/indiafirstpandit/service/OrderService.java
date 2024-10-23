package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Order;
import com.indiafirstpandit.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByPujaId(UUID pujaId) {
        return orderRepository.findByPujaId(pujaId);
    }

    public List<Order> getOrdersBySamagriId(UUID samagriId) {
        return orderRepository.findBySamagriId(samagriId);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
