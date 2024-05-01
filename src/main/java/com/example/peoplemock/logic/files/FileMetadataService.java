package com.example.peoplemock.logic.files;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Component
public class FileMetadataService {


    private List<FileMetadata> filesMeta = new ArrayList<>();
    private FileMetadata fileMetadata;

    public void saveFileMetadata(FileMetadata fileMetadata) {
        filesMeta.add(fileMetadata);

    }


}
