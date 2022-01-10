package jpabook.jpashop.domain;

import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue
    private Long Id;

    @Embedded
    private Address address;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;
}