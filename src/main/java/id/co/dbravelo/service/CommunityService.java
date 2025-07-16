package id.co.dbravelo.service;

import id.co.dbravelo.model.Community;
import id.co.dbravelo.vo.CommunityVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommunityService {
    Community createPost(CommunityVo vo);
    List<Community> getAllPosts();

}

