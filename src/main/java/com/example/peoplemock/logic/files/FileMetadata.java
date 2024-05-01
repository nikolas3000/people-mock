package com.example.peoplemock.logic.files;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileMetadata  {
    private String fileUuid;
    private String fileName;
    private String fileType;
    private long fileSize;
    private LocalDateTime uploadDate;

    public FileMetadata (String fileUuid, String fileName, String fileType, long fileSize, LocalDateTime uploadDate){
        super();
    }
}
