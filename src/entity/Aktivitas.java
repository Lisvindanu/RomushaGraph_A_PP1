package entity;

public class Aktivitas {
    private String idAktivitas;
    private User pengguna;
    private String jenisAktivitas, deskripsi, waktu, target;

    public Aktivitas(String idAktivitas, User pengguna, String jenisAktivitas, String deskripsi) {
        this.idAktivitas = idAktivitas;
        this.pengguna = pengguna;
        this.jenisAktivitas = jenisAktivitas;
        this.deskripsi = deskripsi;
        this.waktu = java.time.LocalDateTime.now().toString();
        this.target = " ";
    }

    public Aktivitas(String idAktivitas, User pengguna, String jenisAktivitas, String deskripsi, String target) {
        this.idAktivitas = idAktivitas;
        this.pengguna = pengguna;
        this.jenisAktivitas = jenisAktivitas;
        this.deskripsi = deskripsi;
        this.waktu = java.time.LocalDateTime.now().toString();
        this.target = target;
    }

    public String getIdAktivitas() {
        return idAktivitas;
    }

    public User getPengguna() {
        return pengguna;
    }

    public String getJenisAktivitas() {
        return jenisAktivitas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return  "[" + jenisAktivitas + "] " + pengguna.getUsername() + ": " + deskripsi + (target.isEmpty() ? "" : " " + target); // ternary
    }
}
