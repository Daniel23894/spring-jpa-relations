package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderDto(Long id, OrderStatus status, LocalDate orderDate) {
}
