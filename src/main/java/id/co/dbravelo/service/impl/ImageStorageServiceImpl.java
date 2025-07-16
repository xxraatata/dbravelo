package id.co.dbravelo.service.impl;

import id.co.dbravelo.service.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    private final String uploadDir = "uploads/community";

    @Override
    public List<String> saveImages(List<MultipartFile> images) {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : images) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir + File.separator + filename);
            try {
                file.transferTo(dest);
                paths.add("/" + uploadDir + "/" + filename); // Sesuai path public
            } catch (IOException e) {
                throw new RuntimeException("Gagal simpan gambar: " + e.getMessage());
            }
        }
        return paths;
    }
}

