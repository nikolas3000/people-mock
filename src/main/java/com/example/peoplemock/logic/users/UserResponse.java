package com.example.peoplemock.logic.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserResponse {


    private String firstName;
    private String lastName;
    public UserResponse() {

        super();
    }

}
