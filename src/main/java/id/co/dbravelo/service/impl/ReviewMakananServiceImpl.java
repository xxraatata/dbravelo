package id.co.dbravelo.service.impl;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.model.Kuliner;
import id.co.dbravelo.model.ReviewFoto;
import id.co.dbravelo.model.ReviewMakanan;
import id.co.dbravelo.repository.KulinerRepo;
import id.co.dbravelo.repository.ReviewFotoRepo;
import id.co.dbravelo.repository.ReviewMakananRepo;
import id.co.dbravelo.service.ReviewMakananService;
import id.co.dbravelo.vo.ReviewMakananVoForm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewMakananServiceImpl implements ReviewMakananService {

    @Autowired
    private ReviewMakananRepo reviewRepo;
    @Autowired
    private KulinerRepo kulinerRepo;
    @Autowired
    private ReviewFotoRepo reviewFotoRepo;

    @Override
    public dtoResponse add(ReviewMakananVoForm form) {
        try {
            Optional<Kuliner> kuliner = kulinerRepo.findById(form.getMakananId());
            if (!kuliner.isPresent()) {
                return new dtoResponse(404, null, "Kuliner tidak ditemukan");
            }

            ReviewMakanan review = new ReviewMakanan();
            review.setUserId(form.getUserId());
            review.setMakananId(form.getMakananId());
            review.setRating(form.getRating());
            review.setReviewText(form.getReviewText());
            review.setFotoMakanan(form.getFotoMakanan());
            review.setCreatedBy(form.getCreatedBy());
            review.setCreatedAt(new Date());
            review.setStatus(1);

            reviewRepo.save(review);

            if (form.getFotoMakanan() != null && form.getFotoMakanan().contains(",")) {
                String[] urls = form.getFotoMakanan().split(",");
                review.setFotoMakanan(urls[0].trim());
                for (String url : urls) {
                    ReviewFoto foto = new ReviewFoto();
                    foto.setReviewId(review.getReviewId());
                    foto.setUrl(url.trim());
                    foto.setCreatedAt(new Date());
                    foto.setCreatedBy(form.getCreatedBy());

                    reviewFotoRepo.save(foto);
                }
            }

            return new dtoResponse(200, review, "Review berhasil ditambahkan");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal menambahkan review: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse update(ReviewMakananVoForm reviewMakanan) {
        try {
            Optional<ReviewMakanan> existing = reviewRepo.findById(reviewMakanan.getReviewId());
            if (!existing.isPresent()) {
                return new dtoResponse(404,null, "Review tidak ditemukan");
            }

            ReviewMakanan review = existing.get();
            review.setRating(reviewMakanan.getRating());
            review.setReviewText(reviewMakanan.getReviewText());
            review.setFotoMakanan(reviewMakanan.getFotoMakanan());
            review.setModifiedAt(new Date());
            review.setModifiedBy(reviewMakanan.getModifiedBy());

            reviewRepo.save(review);

            return new dtoResponse(200,review, "Review berhasil diupdate");
        } catch (Exception e) {
            return new dtoResponse(500,null, "Gagal mengupdate review: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse delete(ReviewMakananVoForm reviewMakanan) {
        try {
            Optional<ReviewMakanan> existing = reviewRepo.findById(reviewMakanan.getReviewId());
            if (!existing.isPresent()) {
                return new dtoResponse(404,null, "Review tidak ditemukan");
            }

            ReviewMakanan review = existing.get();
            review.setStatus(0);
            review.setModifiedAt(new Date());
            review.setModifiedBy(reviewMakanan.getModifiedBy());

            reviewRepo.save(review);

            return new dtoResponse(200,review, "Review berhasil dihapus");
        } catch (Exception e) {
            return new dtoResponse(500,null, "Gagal menghapus review: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse getByKulinerId(int id) {
        try {
            List<ReviewMakanan> reviews = reviewRepo.findByMakananId(id);
            return new dtoResponse(200, reviews,"Success");
        } catch (Exception e) {
            e.printStackTrace();
            return new dtoResponse(500,null, "Gagal mengambil review: " + e.getMessage());
        }
    }
}
