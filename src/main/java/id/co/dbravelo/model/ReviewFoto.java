package id.co.dbravelo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trs_review_foto")
public class ReviewFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    public ReviewFoto() {
    }

    public ReviewFoto(Integer id, Integer reviewId, String url, Date createdAt, String createdBy) {
        this.id = id;
        this.reviewId = reviewId;
        this.url = url;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
