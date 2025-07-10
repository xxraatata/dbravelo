package id.co.dbravelo.repository;

import id.co.dbravelo.model.ReviewFoto;
import id.co.dbravelo.model.ReviewMakanan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewFotoRepo extends JpaRepository<ReviewFoto, Integer> {
    List<ReviewFoto> findByReviewId(int reviewId);
}
