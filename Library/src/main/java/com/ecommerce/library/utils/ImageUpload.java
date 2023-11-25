package com.ecommerce.library.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {
    private final String UPLOAD_FOLDER = "G:\\HOTEL_SERVICE_WEBSITE\\Admin\\src\\main\\resources\\static\\img";

    public boolean uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false; // Không có file hoặc file trống, không cần thực hiện gì cả.
        }

        try {
            Path filePath = Paths.get(UPLOAD_FOLDER, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkExist(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return false; // Không có file hoặc file trống, không tồn tại.
        }

        Path filePath = Paths.get(UPLOAD_FOLDER, multipartFile.getOriginalFilename());
        return Files.exists(filePath);
    }
}
