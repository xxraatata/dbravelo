package id.co.dbravelo.dao;

import id.co.dbravelo.vo.KulinerbyResto;
import id.co.dbravelo.vo.RestoranVo;
import id.co.dbravelo.vo.ReviewMakananVo;

import java.util.List;

public interface ReviewMakananDao {
    ReviewMakananVo getReviewById(int id);
    List<ReviewMakananVo> getReviewByRestoran(int id);
    List<ReviewMakananVo> getReviewByUser(int id);
    List<KulinerbyResto> getKuliner(double lat, double lng);
}
