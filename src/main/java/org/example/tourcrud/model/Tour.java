package org.example.tourcrud.model;

import javax.persistence.*;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String destination;
    private double price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Tour() {
    }

    public Tour(Long id, String code, String destination, double price, String image, Type type) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
        this.image = image;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
