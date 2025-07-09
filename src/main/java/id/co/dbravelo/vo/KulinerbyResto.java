package id.co.dbravelo.vo;

public class KulinerbyResto {
    private int kulinerId;
    private String namaMakanan;
    private String deskripsi;
    private int harga;
    private String fotoMakanan;
    private String jenisMakanan;
    private int restoranId;
    private String namaRestoran;
    private double latitude;
    private double longitude;
    private String kota;
    private String alamat;
    private double totalRating;
    private double jarakKm;

    public KulinerbyResto() {
    }

    public KulinerbyResto(int kulinerId, String namaMakanan, String deskripsi, int harga, String fotoMakanan, String jenisMakanan, int restoranId, String namaRestoran, double latitude, double longitude, String kota, String alamat, double totalRating, double jarakKm) {
        this.kulinerId = kulinerId;
        this.namaMakanan = namaMakanan;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.fotoMakanan = fotoMakanan;
        this.jenisMakanan = jenisMakanan;
        this.restoranId = restoranId;
        this.namaRestoran = namaRestoran;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kota = kota;
        this.alamat = alamat;
        this.totalRating = totalRating;
        this.jarakKm = jarakKm;
    }

    public int getKulinerId() {
        return kulinerId;
    }

    public void setKulinerId(int kulinerId) {
        this.kulinerId = kulinerId;
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
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

    public int getRestoranId() {
        return restoranId;
    }

    public void setRestoranId(int restoranId) {
        this.restoranId = restoranId;
    }

    public String getNamaRestoran() {
        return namaRestoran;
    }

    public void setNamaRestoran(String namaRestoran) {
        this.namaRestoran = namaRestoran;
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

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    public double getJarakKm() {
        return jarakKm;
    }

    public void setJarakKm(double jarakKm) {
        this.jarakKm = jarakKm;
    }
}
