package com.electronic.store.services.impl;

import com.electronic.store.exceptions.BadApiRequest;
import com.electronic.store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadImage(MultipartFile file, String path) throws IOException {

        String originalFileName = file.getOriginalFilename();
        logger.info("Filename : {}", originalFileName);
        String fileName = UUID.randomUUID().toString();
        assert originalFileName != null;
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = fileName+extension;
        String fullPathWithFileName = path + fileNameWithExtension;

        logger.info("Full image path : {}", fullPathWithFileName);
        if(extension.equalsIgnoreCase(".png") ||
                extension.equalsIgnoreCase(".jpg") ||
                extension.equalsIgnoreCase(".jpeg")){

            logger.info("File extension : {}", extension);
            // file save
            File folder = new File(path);
            if(!folder.exists()){
                // create folder
                folder.mkdirs();
            }
            // file upload
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;

        }else{
            throw new BadApiRequest("File with " + extension + "format not supported");
        }
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
