package entity;

public class Pertemanan {
    private User user1;
    private User user2;
    private String tanggalBerteman, statusPertemanan; // aktif, blokir, pending
    private String keterangan;

    public Pertemanan(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.tanggalBerteman = java.time.LocalDate.now().toString();
        this.statusPertemanan = "aktif";
        this.keterangan = "pertemanan baru";
    }

    public Pertemanan(User user1, User user2, String keterangan) {
        this.user1 = user1;
        this.user2 = user2;
        this.tanggalBerteman = java.time.LocalDate.now().toString();
        this.statusPertemanan = "aktif";
        this.keterangan = keterangan;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public String getTanggalBerteman() {
        return tanggalBerteman;
    }

    public String getStatusPertemanan() {
        return statusPertemanan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public boolean melibatkanUser(String username) {
        return user1.getUsername().equals(username) || user2.getUsername().equals(username);
    }

    public User getTemanDari(String username) {
        if(user1.getUsername().equals(username)) {
            return user2;
        }else if(user2.getUsername().equals(username)) {
            return user1;
        }
        return null;
    }


    @Override
    public String toString() {
        return user1.getUsername() + " <-> " + user2.getUsername() + "(" + statusPertemanan + ") - " + keterangan;
    }
}
