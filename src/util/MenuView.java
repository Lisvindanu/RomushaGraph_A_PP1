package util;

public class MenuView {
    public static int displayMenuUtama() {
        System.out.println("\n=== Sistem Jaringan Pertmenanan ===");
        System.out.println("1. Tambah user baru");
        System.out.println("2. Tambah pertemanan");
        System.out.println("3. Lihat daftar teman");
        System.out.println("4. Cari teman bersama");
        System.out.println("5. Cek Status pertemanan");
        System.out.println("6. Tampilkan jaringan lengkap");
        System.out.println("7. Cari Saran Teman");
        System.out.println("8. Buat Grup Baru");
        System.out.println("9. Tampilkan Semua grup");
        System.out.println("10. Lihat Aktivitas Terbaru");
        System.out.println("11. Statistik Jaringan");
        System.out.println("12. Keluar");
        return InputUtil.inputInt("Pilih Menu");
    }
}
