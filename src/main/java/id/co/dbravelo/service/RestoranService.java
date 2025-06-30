package id.co.dbravelo.service;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.vo.RestoranVo;
import id.co.dbravelo.vo.RestoranVoForm;

import java.util.List;

public interface RestoranService {
    dtoResponse add(RestoranVoForm restoranVoForm);
    dtoResponse edit(RestoranVoForm restoranVoForm);
    dtoResponse delete(RestoranVoForm restoranVoForm);
}
