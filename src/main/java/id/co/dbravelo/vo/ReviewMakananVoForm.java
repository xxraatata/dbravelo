package id.co.dbravelo.vo;

import id.co.dbravelo.model.ReviewMakanan;

public class ReviewMakananVoForm {
    private Integer reviewId;
    private Integer userId;
    private Integer makananId;
    private Integer rating;
    private String reviewText;
    private String fotoMakanan;
    private String createdBy;
    private String modifiedBy;

    public ReviewMakananVoForm() {
    }

    public ReviewMakananVoForm(ReviewMakanan rv) {
        this.reviewId = rv.getReviewId();
        this.userId = rv.getUserId();
        this.makananId = rv.getMakananId();
        this.rating = rv.getRating();
        this.reviewText = rv.getReviewText();
        this.fotoMakanan = rv.getFotoMakanan();
        this.createdBy = rv.getCreatedBy();
        this.modifiedBy = rv.getModifiedBy();
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMakananId() {
        return makananId;
    }

    public void setMakananId(Integer makananId) {
        this.makananId = makananId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getFotoMakanan() {
        return fotoMakanan;
    }

    public void setFotoMakanan(String fotoMakanan) {
        this.fotoMakanan = fotoMakanan;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
