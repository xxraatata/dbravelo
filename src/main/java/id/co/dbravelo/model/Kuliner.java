package id.co.dbravelo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ms_kuliner")
public class Kuliner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kuliner_id")
    private Integer kulinerId;

    @ManyToOne
    @JoinColumn(name = "restoran_id", nullable = false)
    private Restoran restoran;
    @Column(name = "nama_makanan")
    private String namaMakanan;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "harga")
    private Integer harga;
    @Column(name = "foto_makanan")
    private String fotoMakanan;
    @Column(name = "jenis_makanan")
    private String jenisMakanan;
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

    public Kuliner() {
    }

    public Kuliner(Integer kulinerId, Restoran restoran, String namaMakanan, String deskripsi, Integer harga, String fotoMakanan, String jenisMakanan, String createdBy, Date createdAt, String modifiedBy, Date modifiedAt, Integer status) {
        this.kulinerId = kulinerId;
        this.restoran = restoran;
        this.namaMakanan = namaMakanan;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.fotoMakanan = fotoMakanan;
        this.jenisMakanan = jenisMakanan;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.status = status;
    }

    public Integer getKulinerId() {
        return kulinerId;
    }

    public void setKulinerId(Integer kulinerId) {
        this.kulinerId = kulinerId;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
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
