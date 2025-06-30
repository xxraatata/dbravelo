package id.co.dbravelo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ms_restoran")
public class Restoran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restoran_id")
    private Integer restoranId;
    @Column(name = "nama_restoran")
    private String namaRestoran;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "kota")
    private String kota;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "jam_operasional")
    private String jamOperasional;
    @Column(name = "nomor_telepon")
    private String nomorTelepon;
    @Column(name = "foto_restoran")
    private String fotoRestoran;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @Column(name = "status")
    private Integer status;

    public Restoran() {
    }

    public Restoran(Integer restoranId, String namaRestoran, String alamat, String kota, double latitude, double longitude, String jamOperasional, String nomorTelepon, String fotoRestoran, String createdBy, Date createdAt, String modifiedBy, Date modifiedAt, Integer status) {
        this.restoranId = restoranId;
        this.namaRestoran = namaRestoran;
        this.alamat = alamat;
        this.kota = kota;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jamOperasional = jamOperasional;
        this.nomorTelepon = nomorTelepon;
        this.fotoRestoran = fotoRestoran;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.status = status;
    }

    public Integer getRestoranId() {
        return restoranId;
    }

    public void setRestoranId(Integer restoranId) {
        this.restoranId = restoranId;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}