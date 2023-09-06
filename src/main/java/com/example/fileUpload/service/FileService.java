package com.example.fileUpload.service;

import com.example.fileUpload.entity.FileData;
import com.example.fileUpload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public String uploadFile(MultipartFile file) throws IOException {
        if (Objects.nonNull(file)) {
            FileData fileData = FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .build();
            fileRepository.save(fileData);
            return "file upload successfully";
        }
        return null;
    }

    public FileData downloadFile(String fileName) {
        return fileRepository.findByName(fileName).orElse(null);
    }
}
