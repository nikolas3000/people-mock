package com.example.peoplemock.controllers;


import com.example.peoplemock.Logger;
import com.example.peoplemock.ObjectMapperConfig;
import com.example.peoplemock.logic.files.FileMetadata;
import com.example.peoplemock.logic.files.FileMetadataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/files")

public class FilesController {


    private FileMetadataService fileMetadataService;
    private FileMetadata fileMetadata;
    private final Logger logger;

    private final ObjectMapperConfig objectMapperConfig;
    @Autowired
    public FilesController(FileMetadataService fileMetadataService,Logger logger, ObjectMapperConfig objectMapperConfig) {
        this.fileMetadataService = fileMetadataService;
        this.logger = logger;
        this.objectMapperConfig = objectMapperConfig;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public FileMetadata uploadFile(@RequestParam("file") MultipartFile file) {
        logger.getInfoPath("/upload");
/*
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

 */
        String fileUuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        long fileSize = file.getSize();
        LocalDateTime uploadDate = LocalDateTime.now();

        //удалить потом
        try {
            logger.getInfoResponse("uploadDate: " + uploadDate.toString(), "/upload");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        FileMetadata fileMetadata = new FileMetadata(fileUuid, fileName, fileType, fileSize, uploadDate);
//        FileMetadata fileMetadata = new FileMetadata(fileName);

        try {
            logger.getInfoResponse(fileMetadata, "/upload");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        fileMetadataService.saveFileMetadata(fileMetadata);


        return fileMetadata;
    }

}
