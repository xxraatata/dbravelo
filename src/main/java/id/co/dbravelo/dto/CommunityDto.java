package id.co.dbravelo.dto;

import id.co.dbravelo.model.Community;
import id.co.dbravelo.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CommunityDto {
    private Integer id;
    private String content;
    private List<String> images;
    private String user;
    private String avatar;
    private LocalDateTime createdAt;

    public CommunityDto(Community post, User user) {
        this.id = post.getPostId();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();

        if (post.getImagePaths() != null && !post.getImagePaths().isEmpty()) {
            this.images = Arrays.asList(post.getImagePaths().split(","));
        }

        this.user = user != null ? user.getFullName() : "Anonim";
        this.avatar = (user != null && user.getProfilePhoto() != null)
                ? "/user/avatar/" + user.getUserId()
                : null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}