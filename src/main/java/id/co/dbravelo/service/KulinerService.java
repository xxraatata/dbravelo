package id.co.dbravelo.service;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.vo.KulinerVoForm;

public interface KulinerService {
    dtoResponse getAll();
    dtoResponse getById(int id);
    dtoResponse getByRestoId(int id);
    dtoResponse add (KulinerVoForm kulinerVoForm);
    dtoResponse edit(KulinerVoForm kulinerVoForm);
    dtoResponse delete(KulinerVoForm kulinerVoForm);
}
