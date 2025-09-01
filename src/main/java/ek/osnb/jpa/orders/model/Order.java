package ek.osnb.jpa.orders.model;

import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDate orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order() {}

    public Order(LocalDate orderDate, OrderStatus status) {
        this.orderDate = orderDate;
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
