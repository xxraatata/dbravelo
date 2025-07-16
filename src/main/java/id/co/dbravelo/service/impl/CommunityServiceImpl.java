package id.co.dbravelo.service.impl;

import id.co.dbravelo.model.Community;
import id.co.dbravelo.model.CommunityImage;
import id.co.dbravelo.repository.CommunityRepository;
import id.co.dbravelo.service.CommunityService;
import id.co.dbravelo.service.ImageStorageService;
import id.co.dbravelo.vo.CommunityVo;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository communityRepo;

    @Autowired
    private ImageStorageService imageStorageService;
    @Override
    public Community createPost(CommunityVo vo) {
        Community post = new Community();
        post.setUserId(vo.getUserId());
        post.setContent(vo.getContent());
        post.setImagePaths(String.join(",", vo.getImageUrls()));
        post.setCreatedAt(LocalDateTime.now());
        return communityRepo.save(post);
    }

    @Override
    public List<Community> getAllPosts() {
        return communityRepo.findAllByOrderByCreatedAtDesc();
    }
}

