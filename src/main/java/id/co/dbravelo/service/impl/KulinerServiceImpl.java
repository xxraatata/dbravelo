package id.co.dbravelo.service.impl;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.model.Kuliner;
import id.co.dbravelo.model.Restoran;
import id.co.dbravelo.repository.KulinerRepo;
import id.co.dbravelo.repository.RestoranRepo;
import id.co.dbravelo.service.KulinerService;
import id.co.dbravelo.vo.KulinerVo;
import id.co.dbravelo.vo.KulinerVoForm;
import id.co.dbravelo.vo.RestoranVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KulinerServiceImpl implements KulinerService {

    @Autowired
    private KulinerRepo kulinerRepo;

    @Autowired
    private RestoranRepo restoranRepo;

    @Override
    public dtoResponse getAll() {
        try {
            List<Kuliner> list = kulinerRepo.findAllByStatus(1); // hanya data aktif
            return new dtoResponse(200, list, "Berhasil mengambil semua data kuliner");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal mengambil data kuliner: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse getByRestoId(int id) {
        List<Kuliner> list = kulinerRepo.findByRestoranIdActive(id);
        if (list != null) {
            return new dtoResponse(200, list, "Berhasil mendapatkan data kuliner");
        } else {
            return new dtoResponse(404, null, "Restoran tidak ditemukan");
        }
    }

    @Override
    public dtoResponse add(KulinerVoForm kulinerVoForm) {
        try {
            Optional<Restoran> optionalRestoran = restoranRepo.findByIdActive(kulinerVoForm.getRestoranId());
            if (optionalRestoran.isEmpty()) {
                return new dtoResponse(404, null, "Restoran tidak ditemukan");
            }
            Restoran restoran = optionalRestoran.get();

            Kuliner kuliner = new Kuliner();
            kuliner.setRestoran(restoran);
            kuliner.setNamaMakanan(kulinerVoForm.getNamaMakanan());
            kuliner.setDeskripsi(kulinerVoForm.getDeskripsi());
            kuliner.setHarga(kulinerVoForm.getHarga());
            kuliner.setFotoMakanan(kulinerVoForm.getFotoMakanan());
            kuliner.setJenisMakanan(kulinerVoForm.getJenisMakanan());
            kuliner.setCreatedBy(kulinerVoForm.getCreatedBy());
            kuliner.setCreatedAt(new Date());
            kuliner.setStatus(1);

            kulinerRepo.save(kuliner);

            return new dtoResponse(200, kuliner, "Kuliner berhasil ditambahkan");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal menambahkan kuliner: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse edit(KulinerVoForm kulinerVoForm) {
        try {
            Optional<Kuliner> optionalKuliner = kulinerRepo.findById(kulinerVoForm.getKulinerId());
            if (optionalKuliner.isEmpty()) {
                return new dtoResponse(404, null, "Kuliner tidak ditemukan");
            }

            Kuliner kuliner = optionalKuliner.get();

            kuliner.setNamaMakanan(kulinerVoForm.getNamaMakanan());
            kuliner.setDeskripsi(kulinerVoForm.getDeskripsi());
            kuliner.setHarga(kulinerVoForm.getHarga());
            kuliner.setFotoMakanan(kulinerVoForm.getFotoMakanan());
            kuliner.setJenisMakanan(kulinerVoForm.getJenisMakanan());
            kuliner.setModifiedBy(kulinerVoForm.getModifiedBy());
            kuliner.setModifiedAt(new Date());

            kulinerRepo.save(kuliner);

            return new dtoResponse(200, kuliner, "Kuliner berhasil diperbarui");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal mengedit kuliner: " + e.getMessage());
        }
    }

    @Override
    public dtoResponse delete(KulinerVoForm kulinerVoForm) {
        try {
            Optional<Kuliner> optionalKuliner = kulinerRepo.findById(kulinerVoForm.getKulinerId());
            if (optionalKuliner.isEmpty()) {
                return new dtoResponse(404, null, "Kuliner tidak ditemukan");
            }

            Kuliner kuliner = optionalKuliner.get();
            kuliner.setStatus(0);
            kuliner.setModifiedBy(kulinerVoForm.getModifiedBy());
            kuliner.setModifiedAt(new Date());

            kulinerRepo.save(kuliner);

            return new dtoResponse(200, null, "Kuliner berhasil dihapus");
        } catch (Exception e) {
            return new dtoResponse(500, null, "Gagal menghapus kuliner: " + e.getMessage());
        }
    }
}

