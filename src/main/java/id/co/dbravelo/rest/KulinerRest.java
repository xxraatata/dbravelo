package id.co.dbravelo.rest;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.service.KulinerService;
import id.co.dbravelo.vo.KulinerVoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/kuliner")
@CrossOrigin(origins = "*")
public class KulinerRest {

    @Autowired
    private KulinerService kulinerService;

    public KulinerRest(KulinerService kulinerService) {
        this.kulinerService = kulinerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<dtoResponse> getAllKuliner() {
        dtoResponse response = kulinerService.getAll();
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @GetMapping("/getByRestoId")
    public ResponseEntity<dtoResponse> getKulinerByRestoId(@RequestParam("id") int id) {
        dtoResponse response = kulinerService.getByRestoId(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Validasi file kosong
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "File is empty"));
            }

            // Validasi ukuran file (maksimal 10MB)
            if (file.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "File too large (max 10MB)"));
            }

            // Validasi tipe file
            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Only image files allowed"));
            }

            // Validasi nama file
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.contains("..")) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Invalid file name"));
            }

            // Dapatkan ekstensi file
            String fileExtension = "";
            int lastDot = originalFileName.lastIndexOf('.');
            if (lastDot > 0) {
                fileExtension = originalFileName.substring(lastDot);
            }

            // Date untuk nama file
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");
            String timestamp = now.format(formatter);

            String uuid = UUID.randomUUID().toString();
            // Nama file baru
            String fileName = String.format("KULINERIMG_%s_%s%s", timestamp, uuid, fileExtension);

            String folderPath = "src/main/resources/uploads/";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); // buat folder jika belum ada
            }

            // Simpan file
            Path filePath = Paths.get(folderPath, fileName);
            Files.write(filePath, file.getBytes());

            // PERBAIKAN: Kembalikan URL lengkap yang bisa diakses
            // Ganti dengan IP address server Anda
            String imageUrl = "/kuliner/uploads/" + fileName;

            Map<String, String> response = new HashMap<>();
            response.put("filename", fileName);
            response.put("url", imageUrl); // Tambahkan URL lengkap
            response.put("message", "Upload successful");

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Upload failed: " + e.getMessage()));
        }
    }

    // Endpoint untuk serve static files
    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/uploads/").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = "image/jpeg";

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header("Cache-Control", "max-age=3600")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<dtoResponse> addKuliner(@RequestBody KulinerVoForm form) {
        dtoResponse response = kulinerService.add(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @PostMapping("/edit")
    public ResponseEntity<dtoResponse> editKuliner(@RequestBody KulinerVoForm form) {
        dtoResponse response = kulinerService.edit(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @PostMapping("/delete")
    public ResponseEntity<dtoResponse> deleteKuliner(@RequestBody KulinerVoForm form) {
        dtoResponse response = kulinerService.delete(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    private HttpStatus getHttpStatus(Integer status) {
        return switch (status) {
            case 200 -> HttpStatus.OK;
            case 404 -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}

