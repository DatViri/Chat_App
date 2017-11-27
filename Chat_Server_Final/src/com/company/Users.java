package com.company;

import java.util.HashSet;

public class Users {
    private static HashSet<Users> usersList = new HashSet<>();
    private String name;

    public Users(String name) {
        this.name = name;
    }

    public static HashSet<Users> getUsersList() {
        return usersList;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return name.equals(users.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name + "\n";
    }
}
