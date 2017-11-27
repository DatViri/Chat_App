package com.company;

//Object : has insert(), remove(), check() method
public class UserNameList {
    private static UserNameList ourInstance = new UserNameList();

    public static UserNameList getInstance() {
        return ourInstance;
    }

    private UserNameList() {
    }
    public void insert(Users user){
        if(this.usersCheck(user) == false)
        Users.getUsersList().add(user);
    }

    public void remove(Users user){
        if(this.usersCheck(user) == true)
        Users.getUsersList().remove(user);
    }

    public boolean usersCheck(Users usercheck){
        return Users.getUsersList().contains(usercheck);
    }


}
