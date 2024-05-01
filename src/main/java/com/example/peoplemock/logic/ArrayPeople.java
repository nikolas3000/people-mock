package com.example.peoplemock.logic;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ArrayPeople {
    private List<String> userNames;
    public ArrayPeople() {
        // Инициализируем список для хранения имен пользователей
        userNames = new ArrayList<>();
    }
    // Метод для добавления имени пользователя в список
    public void addUserName(String userName) {
        userNames.add(userName);
        //System.out.println("User name added: " + userName);
    }

    // Метод для получения всех имен пользователей
    public List<String> getAllUserNames() {
        return userNames;
    }
}
