package id.co.dbravelo.dao;

import id.co.dbravelo.vo.KulinerVo;

import java.util.List;

public interface KulinerDao {
    List<KulinerVo> getAllKuliner();
    List<KulinerVo> getAllKulinerByRestoID(int restoID);
    List<KulinerVo> getKulinerByID(int id);
}
