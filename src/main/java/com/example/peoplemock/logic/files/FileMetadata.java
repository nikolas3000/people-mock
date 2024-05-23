package com.example.peoplemock.logic.files;


import lombok.Getter;
import lombok.Setter;
import com.example.peoplemock.ObjectMapperConfig;
import java.time.LocalDateTime;

@Getter
@Setter

public class FileMetadata  {
    private String fileUuid;
    private String fileName;
    private String fileType;
    private long fileSize;
    //С датой нужно разобраться, без даты работает
    //private LocalDateTime uploadDate;


    public FileMetadata (String fileUuid, String fileName, String fileType, long fileSize/*, LocalDateTime uploadDate*/){
        this.fileUuid = fileUuid;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        //this.uploadDate = uploadDate;
    }
    /*
    public getFileMetadata (String fileUuid, String fileName, String fileType, long fileSize){

    }

     */
}
