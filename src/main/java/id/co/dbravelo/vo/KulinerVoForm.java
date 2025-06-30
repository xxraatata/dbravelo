package id.co.dbravelo.vo;

import id.co.dbravelo.model.Kuliner;

public class KulinerVoForm {
    private Integer kulinerId;
    private Integer restoranId;
    private String namaMakanan;
    private String deskripsi;
    private Integer harga;
    private String fotoMakanan;
    private String jenisMakanan;
    private String createdBy;
    private String modifiedBy;

    public KulinerVoForm() {
    }

    public KulinerVoForm(Kuliner k) {
        this.kulinerId = k.getKulinerId();
        this.restoranId = k.getRestoran().getRestoranId();
        this.namaMakanan = k.getNamaMakanan();
        this.deskripsi = k.getDeskripsi();
        this.harga = k.getHarga();
        this.fotoMakanan = k.getFotoMakanan();
        this.jenisMakanan = k.getJenisMakanan();
        this.createdBy = k.getCreatedBy();
        this.modifiedBy = k.getModifiedBy();
    }

    public Integer getKulinerId() {
        return kulinerId;
    }

    public void setKulinerId(Integer kulinerId) {
        this.kulinerId = kulinerId;
    }

    public Integer getRestoranId() {
        return restoranId;
    }

    public void setRestoranId(Integer restoranId) {
        this.restoranId = restoranId;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getFotoMakanan() {
        return fotoMakanan;
    }

    public void setFotoMakanan(String fotoMakanan) {
        this.fotoMakanan = fotoMakanan;
    }

    public String getJenisMakanan() {
        return jenisMakanan;
    }

    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
