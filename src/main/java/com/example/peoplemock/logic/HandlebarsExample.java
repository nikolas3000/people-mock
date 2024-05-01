package com.example.peoplemock.logic;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
public class HandlebarsExample {
    private Handlebars handlebars;

    public static final String TEMPLATE_PATH = "com/example/peoplemock/template";

    public HandlebarsExample() {
        handlebars = new Handlebars();

    }
    public String applyTemplate(String templatePath, Map<String, Object> data) {
        try {
            // Компилируем шаблон из файла
            Template template = handlebars.compile(TEMPLATE_PATH);

            // Создаем StringWriter для хранения результата
            StringWriter writer = new StringWriter();

            // Выполняем шаблон с данными и выводим результат
            template.apply(data, writer);

            // Возвращаем результат в формате JSON
            return writer.toString();
        } catch (IOException e) {
            // Обработайте исключение по мере необходимости
            throw new RuntimeException("Ошибка при обработке шаблона: " + e.getMessage(), e);
        }
    }
/*
    public String applyTemplate(String templatePath, Map<String, Object> data) {
        try {
            // Компилируем шаблон из файла
            Template template = handlebars.compile("com/example/peoplemock/template");

            // Создаем StringWriter для хранения результата
            StringWriter writer = new StringWriter();

            // Выполняем шаблон с данными и выводим результат
            template.apply(data, writer);

            // Возвращаем результат в формате JSON
            return writer.toString();
        } catch (IOException e) {
            // Обработайте исключение по мере необходимости
            throw new RuntimeException("Ошибка при обработке шаблона: " + e.getMessage(), e);
        }
    }

*/
}
