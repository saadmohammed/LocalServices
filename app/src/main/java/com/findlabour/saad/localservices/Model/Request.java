package com.findlabour.saad.localservices.Model;

import java.util.List;

public class Request {

    private String phone;
    private String status;
    private String address;
    private String total;
    private List<Order> items;

    public Request() {
    }

    public Request(String phone, String address, String total, List<Order> items) {
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.items = items;
        this.status = "0";  //Default is 0, Placed = 0, Shipping = 1, Shipped = 2
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }
}