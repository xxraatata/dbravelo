package id.co.dbravelo.dao;

import id.co.dbravelo.model.Restoran;
import id.co.dbravelo.vo.RestoranVo;

import java.util.List;

public interface RestoranDao {
    List<RestoranVo> getAllRestoran();
    List<RestoranVo> getRestoranById(int id);
    List<RestoranVo> getRestoranByLocation(double latitude, double longitude);
}
