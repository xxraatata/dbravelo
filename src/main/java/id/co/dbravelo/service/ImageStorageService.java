package id.co.dbravelo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageStorageService {
    List<String> saveImages(List<MultipartFile> images);
}

