package id.co.dbravelo.repository;

import id.co.dbravelo.model.Restoran;
import id.co.dbravelo.model.ReviewMakanan;
import id.co.dbravelo.vo.KulinerbyResto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewMakananRepo extends JpaRepository<ReviewMakanan, Integer> {
    Optional<ReviewMakanan> findByReviewIdAndAndStatus(int id, int status);
    List<ReviewMakanan> findByUserIdAndStatus(int userId, int status);
    @Query(value = """
        SELECT r.review_id, r.makanan_id, r.user_id, r.rating, r.review_text, r.foto_makanan, r.created_at, r.created_by, r.modified_at, r.modified_by, r.status
            FROM trs_review_makanan r
            JOIN ms_kuliner k ON r.makanan_id = k.kuliner_id
            WHERE r.makanan_id = :makananId AND r.status = 1
    """, nativeQuery = true)
    List<ReviewMakanan> findByMakananId(@Param("makananId") int makananId);
    @Query(value = """
        SELECT r.*
        FROM trs_review_makanan r
        JOIN ms_kuliner k ON r.makanan_id = k.kuliner_id
        WHERE k.restoran_id = :restoranId
          AND r.status = 1
    """, nativeQuery = true)
    List<ReviewMakanan> findReviewMakananByRestoranId(@Param("restoranId") int restoranId);
    @Query(value = "EXEC sp_getKuliner :lat, :lon", nativeQuery = true)
    List<Object[]> getKuliner(@Param("lat") double latitude, @Param("lon") double longitude);
}
