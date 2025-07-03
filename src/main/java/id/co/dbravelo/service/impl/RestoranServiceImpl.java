package id.co.dbravelo.service.impl;

import id.co.dbravelo.dao.RestoranDao;
import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.model.Restoran;
import id.co.dbravelo.repository.RestoranRepo;
import id.co.dbravelo.service.RestoranService;
import id.co.dbravelo.vo.RestoranVo;
import id.co.dbravelo.vo.RestoranVoForm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RestoranServiceImpl implements RestoranService {
    @Autowired
    private RestoranRepo restoranRepo;
    @Autowired
    private RestoranDao restoranDao;

    @Override
    public dtoResponse getById(int id) {
        RestoranVo vo = (RestoranVo) restoranDao.getRestoranById(id);
        if (vo != null) {
            return new dtoResponse(200, vo, "Berhasil mendapatkan data restoran");
        } else {
            return new dtoResponse(404, null, "Restoran tidak ditemukan");
        }
    }

    @Override
    public dtoResponse add(RestoranVoForm restoranVoForm) {
        try {
            Restoran restoran = new Restoran();
            restoran.setNamaRestoran(restoranVoForm.getNamaRestoran());
            restoran.setAlamat(restoranVoForm.getAlamat());
            restoran.setKota(restoranVoForm.getKota());
            restoran.setLatitude(restoranVoForm.getLatitude());
            restoran.setLongitude(restoranVoForm.getLongitude());
            restoran.setJamOperasional(restoranVoForm.getJamOperasional());
            restoran.setNomorTelepon(restoranVoForm.getNomorTelepon());
            restoran.setFotoRestoran(restoranVoForm.getFotoRestoran());
            restoran.setCreatedBy(restoranVoForm.getCreatedBy());
            restoran.setCreatedAt(new Date());
            restoran.setStatus(1);

            restoranRepo.save(restoran);

            return new dtoResponse(200, restoran, "Restoran berhasil ditambahkan");
        } catch (Exception e) {
            return new dtoResponse(500, null,  "Gagal menambahkan restoran: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse edit(RestoranVoForm restoranVoForm) {
        try {
            Optional<Restoran> optionalRestoran = restoranRepo.findById(restoranVoForm.getIdResto());
            if (optionalRestoran.isEmpty()) {
                return new dtoResponse(404, null, "Restoran tidak ditemukan");
            }

            Restoran restoran = optionalRestoran.get();
            restoran.setNamaRestoran(restoranVoForm.getNamaRestoran());
            restoran.setAlamat(restoranVoForm.getAlamat());
            restoran.setKota(restoranVoForm.getKota());
            restoran.setLatitude(restoranVoForm.getLatitude());
            restoran.setLongitude(restoranVoForm.getLongitude());
            restoran.setJamOperasional(restoranVoForm.getJamOperasional());
            restoran.setNomorTelepon(restoranVoForm.getNomorTelepon());
            restoran.setFotoRestoran(restoranVoForm.getFotoRestoran());
            restoran.setModifiedBy(restoranVoForm.getModifiedBy());
            restoran.setModifiedAt(new Date());

            restoranRepo.save(restoran);

            return new dtoResponse(200, restoran, "Restoran berhasil diperbarui");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal mengedit restoran: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse delete(RestoranVoForm restoranVoForm) {
        try {
            Optional<Restoran> optionalRestoran = restoranRepo.findById(restoranVoForm.getIdResto());
            if (optionalRestoran.isEmpty()) {
                return new dtoResponse(404, null, "Restoran tidak ditemukan");
            }

            Restoran restoran = optionalRestoran.get();
            restoran.setStatus(0);
            restoran.setModifiedBy(restoranVoForm.getModifiedBy());
            restoran.setModifiedAt(new Date());

            restoranRepo.save(restoran);

            return new dtoResponse(200, null, "Restoran berhasil dihapus");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal menghapus restoran: " + e.getMessage());
        }
    }
}
