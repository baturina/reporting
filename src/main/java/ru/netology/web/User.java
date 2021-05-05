package ru.netology.web;


public class User {
    private final String city;
    private final String localDate;
    private final String name;
    private final String phone;

    public User(String city, String localDate, String name, String phone) {
        this.city = city;
        this.localDate = localDate;
        this.name = name;
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public String getLocalDate() {
        return localDate;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
