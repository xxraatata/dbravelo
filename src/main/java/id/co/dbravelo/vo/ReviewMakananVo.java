package id.co.dbravelo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.dbravelo.model.ReviewMakanan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReviewMakananVo {
    private Integer reviewId;
    private Integer userId;
    private Integer makananId;
    private String namaMakanan;
    private Integer rating;
    private String reviewText;
    private String fotoMakanan;
    private String createdBy;
    private Date createdAt;
    private String modifiedBy;

    public ReviewMakananVo() {
    }

    public ReviewMakananVo(ReviewMakanan rv) {
        this.reviewId = rv.getReviewId();
        this.userId = rv.getUserId();
        this.makananId = rv.getMakananId();
        this.rating = rv.getRating();
        this.reviewText = rv.getReviewText();
        this.fotoMakanan = rv.getFotoMakanan();
        this.createdBy = rv.getCreatedBy();
        this.modifiedBy = rv.getModifiedBy();
        this.createdAt = rv.getCreatedAt();
    }
    @JsonProperty("formattedCreatedAtEnglish")
    public String getFormattedCreatedAtEnglish() {
        if (createdAt == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH);
        return sdf.format(createdAt);
    }
    @JsonProperty("formattedCreatedAtIndo")
    public String getFormattedCreatedAtIndo() {
        if (createdAt == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        return sdf.format(createdAt);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
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
