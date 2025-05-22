package services;

import entity.Grup;
import entity.User;

public class GroupService {
    private Grup[] daftarGrup;
    private int jumlahGrup, maxGrup;
    public GraphCore graphCore;

    public GroupService(GraphCore graphCore) {
        this.graphCore = graphCore;
        this.maxGrup = 20;
        this.daftarGrup = new Grup[maxGrup];
        this.jumlahGrup = 0;
    }

    //membuat method untuk grup baru
    public boolean buatGrup(String namaGrup, String deskripsi, String usernameAdmin) {
        if(jumlahGrup >= maxGrup) {
            System.out.println("Gabisa bos, dah penuh");
            return false;
        }

        User admin = graphCore.getUserByUsername(usernameAdmin);
        if(admin == null) {
            System.out.println("User admin ga ketemu!");
            return false;
        }

        String idGrup = "GRP" + String.format("%03d", jumlahGrup + 1);
        Grup grupBaru = new Grup(idGrup, namaGrup, deskripsi, admin);
        daftarGrup[jumlahGrup] = grupBaru;
        jumlahGrup++;

        System.out.println("Grup '" + namaGrup + "' berhasil dibuat dengan id: " + idGrup);
        return true;
    }

    //method untuk nampilin semua grup
    public void tampilkanSemuaGrup() {
        if (jumlahGrup == 0) {
            System.out.println("Belom ada grup bos");
            return;
        }

        System.out.println("\n=== DAFTAR SEMUA GRUP ===");
        for (int i = 0; i < jumlahGrup; i++) {
            if (daftarGrup[i] != null) {
                System.out.println((i+1) + ". " + daftarGrup[i].toString());
                System.out.println("   Deskripsi: " + daftarGrup[i].getDeskripsi());
                System.out.println("   Kategori: " + daftarGrup[i].getKategori());
            }
        }
        System.out.println("===============================");
    }

    public Grup[] cariGrupByNama(String namaGrup) {
        Grup[] hasil = new Grup[jumlahGrup];
        int jumlahHasil = 0;

        for (int i = 0; i < jumlahGrup; i++) {
            if(daftarGrup != null &&
                    daftarGrup[i].getNamaGrup().toLowerCase().contains(namaGrup.toLowerCase())) {
                hasil[jumlahHasil] = daftarGrup[i];
                jumlahHasil++;
            }
        }

        Grup[] hasilFinal = new Grup[jumlahHasil];
        for (int i = 0; i < jumlahHasil; i++) {
            hasilFinal[i] = hasil[i];
        }
        return hasilFinal;
    }

    public void tampilkanGrupByAdmin(String usernameAdmin) {
        boolean found = false;
        System.out.println("\n=== GRUP YANG DIADMIN OLEH  " + usernameAdmin + " ===");

        for (int i = 0; i < jumlahGrup; i++) {
            if(daftarGrup[i] != null && daftarGrup[i].getAdmin().getUsername().equals(usernameAdmin)) {
                System.out.println(" -" + daftarGrup[i].toString());
                found = true;
            }
        }

        if(!found) {
            System.out.println("user " + usernameAdmin + " Belom jadi admin grup manapun");
        }
        System.out.println("==================================");
    }

    public void tampilkanStatistikGroup() {
        if (jumlahGrup == 0) {
            System.out.println("Belom ada grup yang dibuat");
            return;
        }

        System.out.println("\n=== Statistik grup ===");
        System.out.println("Total grup: " + jumlahGrup);

        int totalAnggota = 0;
        Grup grupTerbesar = null;
        int maxAnggota = 0;

        for (int i = 0; i < jumlahGrup; i++) {
            if(daftarGrup[i] != null) {
                totalAnggota += daftarGrup[i].getJumlahAnggota();
                if (daftarGrup[i].getJumlahAnggota() > maxAnggota) {
                    maxAnggota = daftarGrup[i].getJumlahAnggota();
                    grupTerbesar = daftarGrup[i];
                }
            }
        }

        System.out.println("Total anggota semua grup " + totalAnggota);
        if (grupTerbesar != null) {
            System.out.println("Grup terbesar " + grupTerbesar.getNamaGrup() +
            "(" + maxAnggota + "anggota)") ;
        }

        double rataRataAnggota = (double) totalAnggota / jumlahGrup;
        System.out.println("Rata rata anggota per grup : " + String.format("%.1f", rataRataAnggota));
        System.out.println("==================================");

    }

    public int getJumlahGrup() {
        return jumlahGrup;
    }

    public Grup[] getDaftarGrup() {
        return daftarGrup;
    }
}
