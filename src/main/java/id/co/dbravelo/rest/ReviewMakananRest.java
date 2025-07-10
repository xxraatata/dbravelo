package id.co.dbravelo.rest;

import id.co.dbravelo.dao.RestoranDao;
import id.co.dbravelo.dao.ReviewMakananDao;
import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.model.ReviewMakanan;
import id.co.dbravelo.service.RestoranService;
import id.co.dbravelo.service.ReviewMakananService;
import id.co.dbravelo.vo.KulinerbyResto;
import id.co.dbravelo.vo.ReviewMakananVo;
import id.co.dbravelo.vo.ReviewMakananVoForm;
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
import java.util.*;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewMakananRest {

    @Autowired
    private ReviewMakananService reviewService;
    @Autowired
    private ReviewMakananDao reviewDao;

    public ReviewMakananRest(ReviewMakananService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getKuliner")
    public List<KulinerbyResto> getKuliner(@RequestParam double latitude, @RequestParam double longitude){
        return reviewDao.getKuliner(latitude, longitude);
    }

    @GetMapping("/byRestoran")
    public List<ReviewMakananVo> byRestoran(@RequestParam("id") int id) {
        return  reviewDao.getReviewByRestoran(id);
    }

    @GetMapping("/byUser")
    public List<ReviewMakananVo> getByUser(@RequestParam("id") int id) {
        return reviewDao.getReviewByUser(id);
    }

    @GetMapping("/byId")
    public ReviewMakananVo getById(@RequestParam("id") int id) {
        return reviewDao.getReviewById(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "File is empty"));
            }

            if (file.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "File too large (max 10MB)"));
            }

            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Only image files allowed"));
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.contains("..")) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid file name"));
            }

            String fileExtension = "";
            int lastDot = originalFileName.lastIndexOf('.');
            if (lastDot > 0) {
                fileExtension = originalFileName.substring(lastDot);
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            String uuid = UUID.randomUUID().toString();
            String fileName = String.format("REVIEWIMG_%s_%s%s", timestamp, uuid, fileExtension);

            String folderPath = "src/main/resources/uploads/";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            Path filePath = Paths.get(folderPath, fileName);
            Files.write(filePath, file.getBytes());

            String imageUrl = "/kuliner/uploads/" + fileName;

            Map<String, String> response = new HashMap<>();
            response.put("filename", fileName);
            response.put("url", imageUrl);
            response.put("message", "Upload successful");

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Upload failed: " + e.getMessage()));
        }
    }

    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/uploads/").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
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
    public ResponseEntity<dtoResponse> addReview(@RequestBody ReviewMakananVoForm form) {
        dtoResponse response = reviewService.add(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @PostMapping("/edit")
    public ResponseEntity<dtoResponse> editReview(@RequestBody ReviewMakananVoForm form) {
        dtoResponse response = reviewService.update(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @PostMapping("/delete")
    public ResponseEntity<dtoResponse> deleteReview(@RequestBody ReviewMakananVoForm form) {
        dtoResponse response = reviewService.delete(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @GetMapping("/byKuliner")
    public ResponseEntity<dtoResponse> getByKuliner(@RequestParam("id") int kulinerId) {
        dtoResponse response = reviewService.getByKulinerId(kulinerId);
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

