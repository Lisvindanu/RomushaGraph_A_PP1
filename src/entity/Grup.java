package entity;

public class Grup {
    private String idGrup, namaGrup, deskripsi;
    private User admin;
    private String waktuBuat;
    private int maxAnggota, jumlahAnggota;
    private String kategori;

    public Grup(String idGrup, String namaGrup, String deskripsi, User admin) {
        this.idGrup = idGrup;
        this.namaGrup = namaGrup;
        this.deskripsi = deskripsi;
        this.admin = admin;
        this.waktuBuat = java.time.LocalDateTime.now().toString();
        this.maxAnggota = 50;
        this.jumlahAnggota = 1;
        this.kategori = "Komunitas";
    }


    public String getIdGrup() {
        return idGrup;
    }

    public String getWaktuBuat() {
        return waktuBuat;
    }

    public String getNamaGrup() {
        return namaGrup;
    }

    public void setNamaGrup(String namaGrup) {
        this.namaGrup = namaGrup;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getMaxAnggota() {
        return maxAnggota;
    }

    public void setMaxAnggota(int maxAnggota) {
        this.maxAnggota = maxAnggota;
    }

    public int getJumlahAnggota() {
        return jumlahAnggota;
    }

    public void setJumlahAnggota(int jumlahAnggota) {
        this.jumlahAnggota = jumlahAnggota;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public boolean isFull() {
        return jumlahAnggota >= maxAnggota;
    }

    public void tambahAnggota() {
        if (!isFull()) {
            jumlahAnggota = jumlahAnggota + 1;
        }
    }

    public void kurangiAnggota() {
        if (jumlahAnggota > 1) {
            jumlahAnggota = jumlahAnggota - 1;
        }
    }

    @Override
    public String toString() {
        return namaGrup + "(ID: " + idGrup + ") - " + jumlahAnggota + "/" + maxAnggota + " anggota - Admin:" + admin.getUsername();
    }
}
