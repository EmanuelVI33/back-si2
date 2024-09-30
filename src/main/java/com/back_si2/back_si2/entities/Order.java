package com.back_si2.back_si2.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "orders") // Order nombre reservado
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "status")
    private int status = 1; // 1: Pendiente, 2: Cancelado
    @Column(name = "payment_method")
    private int paymentMethod = 1; // 1: Qr, 2: Tigo Money
    @Column(name = "total")
    private double total = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private User user;

    // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // @ToString.Exclude
    // private List<OrderDetail> orderDetails = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDate.now();
    }
}
