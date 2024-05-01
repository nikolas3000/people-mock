package com.example.peoplemock;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Async
public  class Logger {
    public void getInfoPath(String path){
        log.info("");
        log.info("Request:       " + path);
    }

    public <T> void getInfoResponse(T X, String path) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Response:      " + objectMapper.writeValueAsString(X));
        log.info("");
    }
    public void logError(String message, Exception e) {
        log.error(message, e);
    }
}
