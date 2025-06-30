package id.co.dbravelo.dao.impl;

import id.co.dbravelo.dao.RestoranDao;
import id.co.dbravelo.model.Restoran;
import id.co.dbravelo.repository.RestoranRepo;
import id.co.dbravelo.vo.RestoranVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestoranDaoImpl implements RestoranDao {
    @Autowired
    private RestoranRepo restoranRepo;
    @Override
    public List<RestoranVo> getAllRestoran() {
        List<Restoran> data = restoranRepo.findAllActive();
        List<RestoranVo> result = new ArrayList<>();
        for (Restoran item : data) {
            result.add(new RestoranVo(item));
        }
        return result;
    }

    @Override
    public List<RestoranVo> getRestoranById(int id) {
        List<Restoran> data = restoranRepo.findByIdActive(id);
        List<RestoranVo> result = new ArrayList<>();
        for (Restoran item : data) {
            result.add(new RestoranVo(item));
        }
        return result;
    }

    @Override
    public List<RestoranVo> getRestoranByLocation(double latitude, double longitude) {
        List<Restoran> data = restoranRepo.findByLocation(latitude, longitude);
        List<RestoranVo> result = new ArrayList<>();
        for (Restoran item : data) {
            result.add(new RestoranVo(item));
        }
        return result;
    }
}
