package com.example.kobiqoi_laptop.assignment;

public class Account {

    int _id;
    String _name;
    String _username;
    String _email;
    String _password;

    public Account() {}

    public Account(int id, String name, String username, String email, String password) {
        this._id = id;
        this._name = name;
        this._username = username;
        this._email = email;
        this._password = password;
    }

    public Account(String name, String username, String email, String password) {
        this._name = name;
        this._username = username;
        this._email = email;
        this._password = password;
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

    public String getUsername() {
        return this._username;
    }
    public void setUsername(String username) {
        this._username = username;
    }

    public String getEmail() {
        return this._email;
    }
    public void setEmail(String email) {
        this._email = email;
    }

    public String getPassword() {
        return this._password;
    }
    public void setPassword(String password) {
        this._password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _username='" + _username + '\'' +
                ", _email='" + _email + '\'' +
                ", _password='" + _password + '\'' +
                '}';
    }


}
