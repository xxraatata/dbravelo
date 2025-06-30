package id.co.dbravelo.dao.impl;

import id.co.dbravelo.dao.KulinerDao;
import id.co.dbravelo.model.Kuliner;
import id.co.dbravelo.repository.KulinerRepo;
import id.co.dbravelo.vo.KulinerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KulinerDaoImpl implements KulinerDao {
    @Autowired
    private KulinerRepo kulinerRepo;

    @Override
    public List<KulinerVo> getAllKuliner() {
        List<Kuliner> data = kulinerRepo.findAllActive();
        List<KulinerVo> result = new ArrayList<>();
        for (Kuliner item : data) {
            result.add(new KulinerVo(item));
        }
        return result;
    }

    @Override
    public List<KulinerVo> getAllKulinerByRestoID(int restoID) {
        List<Kuliner> data = kulinerRepo.findByRestoranIdActive(restoID);
        List<KulinerVo> result = new ArrayList<>();
        for (Kuliner item : data) {
            result.add(new KulinerVo(item));
        }
        return result;
    }

    @Override
    public List<KulinerVo> getKulinerByID(int id) {
        List<Kuliner> data = kulinerRepo.findByIdActive(id);
        List<KulinerVo> result = new ArrayList<>();
        for (Kuliner item : data) {
            result.add(new KulinerVo(item));
        }
        return result;
    }
}
