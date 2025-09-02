package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Order;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public record OrderMapper() {
    public static OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getStatus(), order.getOrderDate());
    }
}
