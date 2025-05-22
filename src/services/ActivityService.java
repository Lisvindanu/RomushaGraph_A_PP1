package services;

import entity.Aktivitas;
import entity.User;

public class ActivityService {
    private Aktivitas[] daftarAktivitas;
    private int jumlahAktivitas, maxAktivitas;

    public ActivityService() {
        this.maxAktivitas = 200;
        this.daftarAktivitas = new Aktivitas[maxAktivitas];
        this.jumlahAktivitas = 0;
    }

    public boolean tambahAktifitas(Aktivitas aktivitas) {
        if (jumlahAktivitas >= maxAktivitas) {
            //jika penuh, hapus aktifitas terlama (first in first out)
            for (int i = 0; i < maxAktivitas - 1; i++) {
                daftarAktivitas[i] = aktivitas;
            }
            daftarAktivitas[maxAktivitas - 1] = aktivitas;
            return true;
        }
        daftarAktivitas[jumlahAktivitas] = aktivitas;
        jumlahAktivitas++;
        return true;
    }

    public void tampilkanAktivitasTerbaru() {
        if (jumlahAktivitas == 0) {
            System.out.println("Belum ada aktivitas");
            return;
        }

        System.out.println("\n=== Aktivitas terbaru ===");
        //tampilkan 10 aktivitas terbaru
        int start = Math.max(0, jumlahAktivitas - 10);
        for (int i = 0; i < jumlahAktivitas; i++) {
            if (daftarAktivitas[i] != null) {
                System.out.println(jumlahAktivitas - i + ". " + daftarAktivitas[i].toString());
            }
        }
        System.out.println("==================================");
    }

    //method untuk menampilkan aktivitas berdasarkan user
    public void tampilkanAktivitasUser(String username){
        boolean found = false;
        System.out.println("\n=== Aktivitas  " + username.toUpperCase() + " ===");

        for (int i = jumlahAktivitas - 1; i >= 0; i--) {
            if (daftarAktivitas[i] != null &&
            daftarAktivitas[i].getPengguna().getUsername().equals(username)) {
                System.out.println(" -" + daftarAktivitas[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("belom ada aktivitas yang dilakuin sama " + username);
        }
        System.out.println("==================================");
    }

    //METHOD UNTUK MENAmpilkan aktivitas berdasarkan jenis
    public void tampilkanAktivitasByJenis(String jenisAktivitas) {
        boolean found = false;
        System.out.println("\n=== Aktivitas  " + jenisAktivitas.toUpperCase() + " ===");

        for (int i = jumlahAktivitas - 1; i >= 0; i--) {
            if (daftarAktivitas[i] != null &&
                    daftarAktivitas[i].getJenisAktivitas().equals(jenisAktivitas)) {
                System.out.println(" -" + daftarAktivitas[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("belom ada aktivitas jenis :  " + jenisAktivitas);
        }
        System.out.println("==================================");
    }

    public void tampilkanStatistikAktivitas() {
        if(jumlahAktivitas == 0){
            System.out.println("Belum ada aktivitas");
            return;
        }

        System.out.println("\n=== Statistik Aktivitas ===");
        System.out.println("Total aktivitas : " + jumlahAktivitas);

        //hitung aktivitas per jenis
        int[] countPerJenis = new int[5]; // regis, add fren, create grup, join grup, post
        String[] namajenis = {"register", "add_friend", "create_group", "join_group", "post" };

        for(int i=0; i < jumlahAktivitas; i++) {
            if (daftarAktivitas[i] != null) {
                String jenis = daftarAktivitas[i].getJenisAktivitas();
                for(int j=0; j < namajenis.length; j++) {
                    if (jenis.equals(namajenis[j])) {
                        countPerJenis[i]++;
                        break;
                    }
                }
            }
        }
        System.out.println("\nAktivitas per jenis:");
        for(int i=0; i < countPerJenis.length; i++) {
            if (countPerJenis[i] > 0) {
                System.out.println("- "+namajenis[i]+": "+countPerJenis[i]+" kali");
            }
        }
        System.out.println("==================================");
    }

    //method untuk membuat aktivitas dengan mudah
    public void buatAktivitas(User pengguna, String jenis, String deskripsi) {
        String id = "ACT" + String.format("%3d", jumlahAktivitas + 1);
        Aktivitas aktivitas = new Aktivitas(id, pengguna, jenis, deskripsi);
        tambahAktifitas(aktivitas);
    }

    public void buatAktivitas(User pengguna, String jenis, String deskripsi, String target) {
        String id = "ACT" + String.format("%3d", jumlahAktivitas + 1);
        Aktivitas aktivitas = new Aktivitas(id, pengguna, jenis, deskripsi, target);
        tambahAktifitas(aktivitas);
    }

    public int getJumlahAktivitas() {
        return jumlahAktivitas;
    }

    public Aktivitas[] getAktivitas() {
        return daftarAktivitas;
    }


}
