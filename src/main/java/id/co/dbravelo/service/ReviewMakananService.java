package id.co.dbravelo.service;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.vo.ReviewMakananVoForm;

public interface ReviewMakananService {
    dtoResponse add(ReviewMakananVoForm reviewMakanan);
    dtoResponse update(ReviewMakananVoForm reviewMakanan);
    dtoResponse delete(ReviewMakananVoForm reviewMakanan);
    dtoResponse getByKulinerId(int id);
}
