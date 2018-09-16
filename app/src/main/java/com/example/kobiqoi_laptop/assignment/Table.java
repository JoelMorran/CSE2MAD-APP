package com.example.kobiqoi_laptop.assignment;

public class Table {

    int _id;
    String _tablenumber;
    String _tablesize;


    public Table() {}

    public Table(int id, String tablenumber, String tablesize) {
        this._id = id;
        this._tablenumber = tablenumber;
        this._tablesize = tablesize;

    }

    public Table(String tablenumber, String tablesize) {
        this._tablenumber = tablenumber;
        this._tablesize = tablesize;

    }

    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public String getTablenumber() {
        return this._tablenumber;
    }
    public void setTablenumber(String tablenumber) {
        this._tablenumber = tablenumber;
    }

    public String getTablesize() {
        return this._tablesize;
    }
    public void setTablesize(String tablesize) { this._tablesize = tablesize;
    }

    @Override
    public String toString() {
        return "Table{" +
                "_id=" + _id +
                ", _tablenumber='" + _tablenumber + '\'' +
                ", _tablesize='" + _tablesize + '\'' +
                '}';
    }


}
