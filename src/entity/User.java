package entity;

public class User {
    private String username, nama;
    private int usia;
    private String kota, email, waktuDaftar;
    private int jumlahTeman;
    private String statusAktif;

    public User(String username, String nama, int usia, String kota) {
        this.username = username;
        this.nama = nama;
        this.usia = usia;
        this.kota = kota;
        this.email = username + "@gmail.com";
        this.waktuDaftar = java.time.LocalDateTime.now().toString();
        this.jumlahTeman = 0;
        this.statusAktif = "Online";
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public int getUsia() {
        return usia;
    }

    public String getKota() {
        return kota;
    }

    public String getEmail() {
        return email;
    }

    public String getWaktuDaftar() {
        return waktuDaftar;
    }

    public int getJumlahTeman() {
        return jumlahTeman;
    }


    public void setJumlahTeman(int jumlahTeman) {
        this.jumlahTeman = jumlahTeman;
    }

    public String getStatusAktif() {
        return statusAktif;
    }

    public void setStatusAktif(String statusAktif) {
        this.statusAktif = statusAktif;
    }

    @Override
    public String toString() {
        return nama + " (@" + username + ") - " + usia + " tahun dari " + kota + " [" + statusAktif + "]";
    }

    public boolean equals(User user) {
        if(user == null)
            return false; {
            return this.username.equals(user.getUsername());
        }
    }
}
