package org.example.tourcrud.model;

import org.springframework.web.multipart.MultipartFile;

public class TourForm {
    private Long id;
    private String code;
    private String destination;
    private double price;
    private MultipartFile image;
    private Type type;

    public TourForm() {
    }

    public TourForm(Long id, String code, String destination, double price, MultipartFile image, Type type) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
