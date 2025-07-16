package id.co.dbravelo.rest;

import id.co.dbravelo.dto.CommunityDto;
import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.model.Community;
import id.co.dbravelo.model.User;
import id.co.dbravelo.repository.CommunityRepository;
import id.co.dbravelo.repository.UserRepository;
import id.co.dbravelo.service.CommunityService;
import id.co.dbravelo.vo.CommunityVo;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "*")
public class CommunityRest {
    @Autowired
    private CommunityService communityService;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("images") List<MultipartFile> files) {
        List<Map<String, String>> results = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                if (file.isEmpty()) continue;

                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) continue;

                String originalFileName = file.getOriginalFilename();
                if (originalFileName == null || originalFileName.contains("..")) continue;

                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = "COMMIMG_" + UUID.randomUUID() + fileExtension;

                String folderPath = "src/main/resources/uploads/community/";
                File folder = new File(folderPath);
                if (!folder.exists()) folder.mkdirs();

                Path filePath = Paths.get(folderPath, fileName);
                Files.write(filePath, file.getBytes());

                String imageUrl = "/community/uploads/" + fileName;

                Map<String, String> result = new HashMap<>();
                result.put("filename", fileName);
                result.put("url", imageUrl);
                results.add(result);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "Upload gagal: " + e.getMessage()));
            }
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/uploads/community/").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        List<Community> posts = communityRepository.findAllByOrderByCreatedAtDesc();

        List<CommunityDto> result = posts.stream()
                .map(post -> {
                    User user = userRepository.findById(post.getUserId()).orElse(null);
                    return new CommunityDto(post, user);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }




    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody CommunityVo vo) {
        try {
            Community saved = communityService.createPost(vo);
            return ResponseEntity.ok(new dtoResponse(200, saved, "Post berhasil dibuat"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new dtoResponse(500, null, "Gagal simpan post: " + e.getMessage()));
        }
    }


}

