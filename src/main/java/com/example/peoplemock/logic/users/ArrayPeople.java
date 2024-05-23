package com.example.peoplemock.logic.users;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ArrayPeople {
    private List<String> userNames;

    public ArrayPeople() {

        userNames = new ArrayList<>();
    }

    public void addUserName(String userName) {
        userNames.add(userName);
        //System.out.println("User name added: " + userName);
    }

    public List<String> getAllUserNames() {
        return userNames;
    }
}
