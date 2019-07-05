package com.findlabour.saad.localservices.Model;

public class Items {
    private String Name, Image, Price, Quantity,Discount;

    public Items() {
    }


    public Items(String name, String image, String price, String quantity, String discount) {
        Name = name;
        Image = image;
        Price = price;
        Quantity = quantity;
        Discount = discount;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}