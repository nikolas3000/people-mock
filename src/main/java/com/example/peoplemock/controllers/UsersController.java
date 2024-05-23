package com.example.peoplemock.controllers;

import com.example.peoplemock.logic.users.ArrayPeople;
import com.example.peoplemock.logic.api.ApiResponse;
import com.example.peoplemock.logic.users.UserResponse;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.web.bind.annotation.*;
import com.example.peoplemock.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final ArrayPeople arrayPeople;
    private final Logger logger;
    
    @Autowired
    public UsersController(ArrayPeople arrayPeople, Logger logger) {
        this.arrayPeople = arrayPeople;
        this.logger = logger;
    }

    @GetMapping(value = "/fields", produces = "application/json")
    public Map<String, String> getData() {

        Map<String, String> data = new HashMap<>();



        data.put("success", "200 Ok");
        data.put("type", "значение поля2");

        // Возвращаем данные в формате JSON
        return data;
    }

    //метод добавления пользователей в список
    @PostMapping(value = "/addArray", consumes = "application/json")
    public ApiResponse<UserResponse> addPeopleArrayList(@RequestBody Map<String, String> requestData) {


        logger.getInfoPath("/addArray");
        String userUuid = UUID.randomUUID().toString();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");

        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {

            arrayPeople.addUserName(userUuid);
            arrayPeople.addUserName(firstName);
            arrayPeople.addUserName(lastName);


            try {
                logger.getInfoResponse(new UserResponse(userUuid,firstName, lastName), "/addArray");
            } catch (Exception e) {
                logger.logError("Error during logging response: ",e);
            }
            return new ApiResponse<>("success", "User added successfully", new UserResponse(userUuid,firstName, lastName),null);
        } else {

            String errorMessage = "Пустое имя или фамилия";
            logger.logError(errorMessage, new IllegalArgumentException(errorMessage));
            return new  ApiResponse<>("error", errorMessage, null, errorMessage);
        }

    }

    // Метод для получения всех имен пользователей
    @GetMapping("/allPeopleList")
    public ApiResponse<List<String>> getAllUsers() {

        logger.getInfoPath("/allPeopleList");

        List <String> response = arrayPeople.getAllUserNames();

        try {
            logger.getInfoResponse(response, "/allPeopleList");
        } catch (Exception e) {
            logger.logError("Error during logging response: ", e);
            return new ApiResponse<>("error","Error during logging response",null,e.getMessage());
        }
        return new ApiResponse<>("success", "Получен весь список пользователей", response,null);
    }


    @PostMapping(value = "/add", consumes = "application/json")
    public UserResponse echoName(@RequestBody Map<String, String> requestData) {

        String userUuid = UUID.randomUUID().toString();
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");

        return new UserResponse(userUuid,firstName, lastName);
    }

}
