package id.co.dbravelo.dao.impl;

import id.co.dbravelo.dao.ReviewMakananDao;
import id.co.dbravelo.model.ReviewFoto;
import id.co.dbravelo.model.ReviewMakanan;
import id.co.dbravelo.repository.KulinerRepo;
import id.co.dbravelo.repository.ReviewFotoRepo;
import id.co.dbravelo.repository.ReviewMakananRepo;
import id.co.dbravelo.vo.KulinerbyResto;
import id.co.dbravelo.vo.ReviewMakananVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ReviewMakananDaoImpl implements ReviewMakananDao {

    @Autowired
    private ReviewMakananRepo reviewRepo;
    @Autowired
    private KulinerRepo kulinerRepo;
    @Autowired
    private ReviewFotoRepo reviewFotoRepo;

    @Override
    public ReviewMakananVo getReviewById(int id) {
        return reviewRepo.findByReviewIdAndAndStatus(id, 1)
                .map(ReviewMakananVo::new)
                .orElse(null);
    }

    @Override
    public List<ReviewMakananVo> getReviewByRestoran(int id) {
        List<ReviewMakanan> data = reviewRepo.findReviewMakananByRestoranId(id);
        List<ReviewMakananVo> result = new ArrayList<>();

        for (ReviewMakanan reviewMakanan : data) {
            ReviewMakananVo vo = new ReviewMakananVo(reviewMakanan);

            // Set nama makanan
            kulinerRepo.findById(reviewMakanan.getMakananId()).ifPresent(k -> {
                vo.setNamaMakanan(k.getNamaMakanan());
            });

            // Ambil semua foto berdasarkan review_id
            List<ReviewFoto> fotoList = reviewFotoRepo.findByReviewId(reviewMakanan.getReviewId());
            List<String> urls = fotoList.stream().map(ReviewFoto::getUrl).toList();

            // Set jadi satu string dipisah koma
            vo.setFotoMakanan(String.join(",", urls));

            result.add(vo);
        }

        return result;
    }

    @Override
    public List<ReviewMakananVo> getReviewByUser(int id) {
        List<ReviewMakanan> data = reviewRepo.findByUserIdAndStatus(id, 1);
        List<ReviewMakananVo> result = new ArrayList<>();
        for (ReviewMakanan item : data) {
            result.add(new ReviewMakananVo(item));
        }
        return result;
    }

    @Override
    public List<KulinerbyResto> getKuliner(double lat, double lng) {
        List<Object[]> rawData = reviewRepo.getKuliner(lat, lng);
        List<KulinerbyResto> result = new ArrayList<>();

        for (Object[] row : rawData) {
            KulinerbyResto dto = new KulinerbyResto();
            dto.setKulinerId((Integer) row[0]);
            dto.setNamaMakanan((String) row[1]);
            dto.setDeskripsi((String) row[2]);
            dto.setHarga((Integer) row[3]);
            dto.setFotoMakanan((String) row[4]);
            dto.setJenisMakanan((String) row[5]);
            dto.setRestoranId((Integer) row[6]);
            dto.setNamaRestoran((String) row[7]);
            dto.setLatitude((Double) row[8]);
            dto.setLongitude((Double) row[9]);
            dto.setKota((String) row[10]);
            dto.setAlamat((String) row[11]);
            dto.setTotalRating((Double) row[12]);
            dto.setJarakKm((Double) row[13]);

            result.add(dto);
        }

        return result;
    }
}
