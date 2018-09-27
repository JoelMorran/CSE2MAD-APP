package com.example.kobiqoi_laptop.assignment;

public class Order {
    int _id;
    String _name;
    String _extra;
    String _amount;
    String _note;
    String _price;
    String _cost;
    String _tableid;

    public Order() {}

    public Order(int id, String name, String extra, String amount, String note, String price, String cost, String tableid) {
        this._id = id;
        this._name = name;
        this._extra = extra;
        this._amount = amount;
        this._note = note;
        this._price = price;
        this._cost = cost;
        this._tableid = tableid;
    }

    public Order(String name, String extra, String amount, String note, String price, String cost, String tableid) {
        this._name = name;
        this._extra = extra;
        this._amount = amount;
        this._note = note;
        this._price = price;
        this._cost = cost;
        this._tableid = tableid;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }
    public void setName(String name) {
        this._name = name;
    }

    public String getExtra() {
        return this._extra;
    }
    public void setExtra(String extra) {
        this._extra = extra;
    }

    public String getAmount() {
        return this._amount;
    }
    public void setAmount(String amount) {
        this._amount = amount;
    }

    public String getNote() {
        return this._note;
    }
    public void setNote(String note) {
        this._note = note;
    }

    public String getPrice() {
        return this._price;
    }
    public void setPrice(String price) {
        this._price = price;
    }

    public String getCost() {
        return this._cost;
    }
    public void setCost(String cost) {
        this._cost = cost;
    }

    public String getTableid() {
        return this._tableid;
    }
    public void setTableid(String tableid) {
        this._tableid = tableid;
    }


    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _extra='" + _extra + '\'' +
                ", _amount='" + _amount + '\'' +
                ", _note='" + _note + '\'' +
                ", _price=" + _price +
                ", _cost=" + _cost +
                ", _tableid=" + _tableid +
                '}';
    }


}