package id.co.dbravelo.rest;

import id.co.dbravelo.dtoResponse;
import id.co.dbravelo.service.KulinerService;
import id.co.dbravelo.vo.KulinerVoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kuliner")
public class KulinerRest {

    @Autowired
    private KulinerService kulinerService;

    public KulinerRest(KulinerService kulinerService) {
        this.kulinerService = kulinerService;
    }

    @PostMapping("/add")
    public ResponseEntity<dtoResponse> addKuliner(@RequestBody KulinerVoForm form) {
        dtoResponse response = kulinerService.add(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @PostMapping("/edit")
    public ResponseEntity<dtoResponse> editKuliner(@RequestBody KulinerVoForm form) {
        dtoResponse response = kulinerService.edit(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    @PostMapping("/delete")
    public ResponseEntity<dtoResponse> deleteKuliner(@RequestBody KulinerVoForm form) {
        dtoResponse response = kulinerService.delete(form);
        return new ResponseEntity<>(response, getHttpStatus(response.getStatus()));
    }

    private HttpStatus getHttpStatus(Integer status) {
        return switch (status) {
            case 200 -> HttpStatus.OK;
            case 404 -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}

