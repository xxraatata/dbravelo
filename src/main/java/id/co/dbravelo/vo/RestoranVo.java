package id.co.dbravelo.vo;

import id.co.dbravelo.model.Restoran;

public class RestoranVo {
    private int id;
    private String namaRestoran;
    private String alamat;
    private String kota;
    private double latitude;
    private double longitude;
    private String jamOperasional;
    private String nomorTelepon;
    private String fotoRestoran;

    public RestoranVo() {
    }

    public RestoranVo(Restoran r) {
        this.id = r.getRestoranId();
        this.namaRestoran = r.getNamaRestoran();
        this.alamat = r.getAlamat();
        this.kota = r.getKota();
        this.latitude = r.getLatitude();
        this.longitude = r.getLongitude();
        this.jamOperasional = r.getJamOperasional();
        this.nomorTelepon = r.getNomorTelepon();
        this.fotoRestoran = r.getFotoRestoran();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaRestoran() {
        return namaRestoran;
    }

    public void setNamaRestoran(String namaRestoran) {
        this.namaRestoran = namaRestoran;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }

    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getFotoRestoran() {
        return fotoRestoran;
    }

    public void setFotoRestoran(String fotoRestoran) {
        this.fotoRestoran = fotoRestoran;
    }
}
