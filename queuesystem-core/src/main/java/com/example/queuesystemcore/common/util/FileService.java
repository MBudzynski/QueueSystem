package com.example.queuesystemcore.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Slf4j
@Service
public class FileService implements FileManagement {

    public String loadFile(String fileName) throws FileNotFoundException {
        try {
            Resource resource = new ClassPathResource(fileName);
            return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(resource.getURI())));
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
