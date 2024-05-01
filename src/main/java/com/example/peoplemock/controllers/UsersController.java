package com.example.peoplemock.controllers;

import com.example.peoplemock.logic.ArrayPeople;
import com.example.peoplemock.logic.api.ApiResponse;
import com.example.peoplemock.logic.users.UserResponse;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.web.bind.annotation.*;
import com.example.peoplemock.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // Создаем объект для данных
        Map<String, String> data = new HashMap<>();


        // Добавляем поля success и type
        data.put("success", "200 Ok");
        data.put("type", "значение поля2");

        // Возвращаем данные в формате JSON
        return data;
    }

    //метод добавления пользователей в список
    @PostMapping(value = "/addArray", consumes = "application/json")
    public ApiResponse<UserResponse> addPeopleArrayList(@RequestBody Map<String, String> requestData) {

        // Логирование запроса
        logger.getInfoPath("/addArray");

        // Получаем имя из запроса
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        // Проверяем, что имя не равно null и не пустое
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            // Добавляем имя в массив userNames
            arrayPeople.addUserName(firstName);
            arrayPeople.addUserName(lastName);

            // Логирование успешного добавления пользователя
            try {
                logger.getInfoResponse(new UserResponse(firstName, lastName), "/addArray");
            } catch (Exception e) {
                logger.logError("Error during logging response: ",e);
            }
            return new ApiResponse<>("success", "User added successfully", new UserResponse(firstName, lastName),null);
        } else {
            // Логирование ошибки: пустое имя или фамилия
            String errorMessage = "Пустое имя или фамилия";
            logger.logError(errorMessage, new IllegalArgumentException(errorMessage));
            return new  ApiResponse<>("error", errorMessage, null, errorMessage);
        }

    }

    // Метод для получения всех имен пользователей + логгирование
    @GetMapping("/allPeopleList")
    public ApiResponse<List<String>> getAllUsers() {

        // Логирование запроса
        logger.getInfoPath("/allPeopleList");

        List <String> response = arrayPeople.getAllUserNames();
        // Логирование ответа
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
        // Получаем имя из запроса
        String firstName = requestData.get("firstName");
        String lastName = requestData.get("lastName");
        // Возвращаем полученное имя в качестве ответа

        return new UserResponse(firstName, lastName);
    }
    /*
    @GetMapping(value = "/fields")
    public String getData() {
        // Создаем объект для данных
        Map<String, Object> data = new HashMap<>();
        data.put("success", "200 Ok");
        data.put("type", "значение поля2");

        // Определяем путь к шаблону
        String templatePath = TEMPLATE_PATH ;

        // Используем handlebarsExample для обработки данных с шаблоном
        String jsonResult = handlebarsExample.applyTemplate(templatePath, data);

        // Возвращаем результат в формате JSON
        return jsonResult;
    }

   @GetMapping("/template")
    public String applyTemplate() {
        // Создаем данные для шаблона
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("age", 30);
        data.put("city", "New York");

        // Используем HandlebarsExample для шаблонизации данных
        return handlebarsExample.applyTemplate("com/example/peoplemock/template", data);
    }

    @Autowired
    private final ArrayPeople userService;


    public UsersController(ArrayPeople userService) {
        this.userService = userService;
    }
    // Метод для добавления пользователя в список

    @PostMapping("/add")
    public void addUser(@RequestParam String userName) {
        userService.addUserName(userName);
    }

    // Метод для получения всех имен пользователей
    @GetMapping("/all")
    public List<String> getAllUsers() {
        return userService.getAllUserNames();
    }
*/
}
