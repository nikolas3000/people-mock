package com.example.peoplemock.controllers;


import com.example.peoplemock.Logger;
import com.example.peoplemock.logic.files.FileMetadata;
import com.example.peoplemock.logic.files.FileMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private  FileMetadataService fileMetadataService;
    private final Logger logger;

    public FilesController(Logger logger) {
        this.logger = logger;

    }

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity <FileMetadata> uploadFile(@RequestParam("file") MultipartFile file){
        logger.getInfoPath("/upload");
        //Проверка не пустой ли файл
        if (file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        String fileUuid = UUID.randomUUID().toString();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        long fileSize = file.getSize();
        LocalDateTime uploadDate= LocalDateTime.now();
        logger.getInfoResponse(fileMetadata, "/upload");
        // Создаем объект FileMetadata для хранения информации о файле
        FileMetadata fileMetadata= new FileMetadata(fileUuid,fileName,fileType,fileSize,uploadDate);

        // Сохраняем метаданные файла в базу данных с помощью сервиса
        fileMetadataService.saveFileMetadata(fileMetadata);

        // Возвращаем метаданные файла в ответе
        return ResponseEntity.ok(fileMetadata);
    }
}
