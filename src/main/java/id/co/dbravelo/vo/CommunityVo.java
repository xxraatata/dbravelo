package id.co.dbravelo.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CommunityVo {
    private Integer userId;
    private String content;
    private List<String> imageUrls;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
