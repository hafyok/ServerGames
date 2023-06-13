package com.example.servergames.Model;

import com.example.servergames.Model.POJO.CRUDUser;

public class UserDataHolder {
    private static UserDataHolder instance;
    private CRUDUser currentUser;

    private UserDataHolder() {
        // Приватный конструктор для предотвращения создания экземпляров класса
    }

    public static synchronized UserDataHolder getInstance() {
        if (instance == null) {
            instance = new UserDataHolder();
        }
        return instance;
    }

    public void setCurrentUser(CRUDUser user) {
        this.currentUser = user;
    }

    public CRUDUser getCurrentUser() {
        return currentUser;
    }
}