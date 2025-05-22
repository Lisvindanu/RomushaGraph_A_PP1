package util;

import entity.User;
import services.*;

public class SocialMediaUtil {

    // Helper methods untuk functionality
    public static void tambahUserBaru(GraphCore graphCore, ActivityService activityService) {
        System.out.println("\n=== TAMBAH USER BARU ===");
        String username = InputUtil.inputString("Masukkan Username");
        String nama = InputUtil.inputString("Masukkan Nama Lengkap");
        int usia = InputUtil.inputInt("Masukkan Usia");
        String kota = InputUtil.inputString("Masukkan Kota Asal");

        User userBaru = new User(username, nama, usia, kota);
        boolean berhasil = graphCore.tambahUser(userBaru);

        if (berhasil) {
            activityService.buatAktivitas(userBaru, "register", "Bergabung dengan jaringan sosial");
            System.out.println("User " + userBaru.getNama() + " (@" + userBaru.getUsername() + ") berhasil ditambahkan!");
        }
    }

    public static void tambahPertemanan(GraphCore graphCore, ActivityService activityService) {
        System.out.println("\n=== TAMBAH PERTEMANAN ===");
        tampilkanSemuaUser(graphCore);
        String user1 = InputUtil.inputString("Masukkan username pertama");
        String user2 = InputUtil.inputString("Masukkan username kedua");

        boolean berhasil = graphCore.tambahPertemanan(user1, user2);
        if (berhasil) {
            User userObj1 = graphCore.getUserByUsername(user1);
            User userObj2 = graphCore.getUserByUsername(user2);

            if (userObj1 != null && userObj2 != null) {
                activityService.buatAktivitas(userObj1, "add_friend", "Berteman dengan " + user2, user2);
                activityService.buatAktivitas(userObj2, "add_friend", "Berteman dengan " + user1, user1);
                System.out.println("Pertemanan antara " + userObj1.getNama() + " dan " + userObj2.getNama() + " berhasil ditambahkan!");
            }
        }
    }

    public static void lihatDaftarTeman(FriendSearchService friendService, GraphCore graphCore) {
        System.out.println("\n=== DAFTAR TEMAN ===");
        tampilkanSemuaUser(graphCore);
        String username = InputUtil.inputString("Masukkan username untuk melihat daftar teman");

        User[] daftarTeman = friendService.getdaftarTeman(username);
        if (daftarTeman != null && daftarTeman.length > 0) {
            System.out.println("\nDaftar teman " + username + ":");
            for (int i = 0; i < daftarTeman.length; i++) {
                if (daftarTeman[i] != null) {
                    System.out.println("- " + daftarTeman[i].getNama() + " (@" + daftarTeman[i].getUsername() +
                            ") dari " + daftarTeman[i].getKota());
                }
            }
        } else {
            System.out.println("User tidak ditemukan atau belum memiliki teman.");
        }
    }

    public static void cariTemanBersama(FriendSearchService friendService, GraphCore graphCore) {
        System.out.println("\n=== CARI TEMAN BERSAMA ===");
        tampilkanSemuaUser(graphCore);
        String userA = InputUtil.inputString("Masukkan username pertama");
        String userB = InputUtil.inputString("Masukkan username kedua");

        User[] mutualFriends = friendService.cariTemanBersama(userA, userB);
        if (mutualFriends != null && mutualFriends.length > 0) {
            System.out.println("\nTeman bersama " + userA + " dan " + userB + ":");
            for (int i = 0; i < mutualFriends.length; i++) {
                if (mutualFriends[i] != null) {
                    System.out.println("- " + mutualFriends[i].getNama() + " (@" + mutualFriends[i].getUsername() + ")");
                }
            }
        } else {
            System.out.println("Tidak ada teman bersama atau user tidak ditemukan.");
        }
    }

    public static void cekStatusPertemanan(GraphCore graphCore) {
        System.out.println("\n=== CEK STATUS PERTEMANAN ===");
        tampilkanSemuaUser(graphCore);
        String check1 = InputUtil.inputString("Masukkan username pertama");
        String check2 = InputUtil.inputString("Masukkan username kedua");

        boolean berteman = graphCore.cekPertemanan(check1, check2);
        if (berteman) {
            System.out.println(check1 + " dan " + check2 + " adalah teman!");
        } else {
            System.out.println(check1 + " dan " + check2 + " bukan teman.");
        }
    }

    public static void cariSaranTeman(FriendSearchService friendService, GraphCore graphCore) {
        System.out.println("\n=== SARAN TEMAN ===");
        tampilkanSemuaUser(graphCore);
        String userSaran = InputUtil.inputString("Masukkan username untuk mendapat saran teman");

        User[] saranTeman = friendService.cariSaranTeman(userSaran);
        if (saranTeman != null && saranTeman.length > 0) {
            System.out.println("\nSaran teman untuk " + userSaran + ":");
            for (int i = 0; i < saranTeman.length; i++) {
                if (saranTeman[i] != null) {
                    System.out.println("- " + saranTeman[i].getNama() + " (@" + saranTeman[i].getUsername() +
                            ") dari " + saranTeman[i].getKota());
                }
            }
        } else {
            System.out.println("Tidak ada saran teman atau user tidak ditemukan.");
        }
    }

    public static void buatGrupBaru(GroupService groupService, GraphCore graphCore, ActivityService activityService) {
        System.out.println("\n=== BUAT GRUP BARU ===");
        tampilkanSemuaUser(graphCore);
        String namaGrup = InputUtil.inputString("Masukkan nama grup");
        String deskripsiGrup = InputUtil.inputString("Masukkan deskripsi grup");
        String adminGrup = InputUtil.inputString("Masukkan username admin grup");

        boolean berhasil = groupService.buatGrup(namaGrup, deskripsiGrup, adminGrup);
        if (berhasil) {
            User admin = graphCore.getUserByUsername(adminGrup);
            if (admin != null) {
                activityService.buatAktivitas(admin, "create_group", "Membuat grup: " + namaGrup);
            }
        }
    }

    public static void tampilkanSemuaUser(GraphCore graphCore) {
        int jumlahUser = graphCore.getJumlahUser();
        if (jumlahUser == 0) {
            System.out.println("Belum ada user yang terdaftar.");
            return;
        }

        System.out.println("\n=== DAFTAR SEMUA USER ===");
        for (int i = 0; i < jumlahUser; i++) {
            User user = graphCore.getUsersByIndex(i);
            if (user != null) {
                System.out.println((i + 1) + ". " + user.toString());
            }
        }
        System.out.println("========================");
    }

    public static void tampilkanJaringan(GraphCore graphCore) {
        int jumlahUser = graphCore.getJumlahUser();
        if (jumlahUser == 0) {
            System.out.println("Belum ada user yang terdaftar.");
            return;
        }

        boolean[][] adjacencyMatrix = graphCore.getAdjacencyMatrix();
        System.out.println("\n=== JARINGAN PERTEMANAN ===");

        for (int i = 0; i < jumlahUser; i++) {
            User user = graphCore.getUsersByIndex(i);
            if (user != null) {
                System.out.print(user.getUsername() + " -> ");

                boolean adaTeman = false;
                for (int j = 0; j < jumlahUser; j++) {
                    if (adjacencyMatrix[i][j]) {
                        if (adaTeman) System.out.print(", ");
                        System.out.print(graphCore.getUsersByIndex(j).getUsername());
                        adaTeman = true;
                    }
                }

                if (!adaTeman) {
                    System.out.print("(belum ada teman)");
                }
                System.out.println();
            }
        }
        System.out.println("==========================");
    }

    public static void tampilkanStatistik(GraphCore graphCore, GroupService groupService,
                                          ActivityService activityService, FriendSearchService friendService) {
        System.out.println("\n=== STATISTIK JARINGAN ===");
        System.out.println("Total User: " + graphCore.getJumlahUser());
        System.out.println("Total Pertemanan: " + graphCore.getJumlahPertemanan());
        System.out.println("Total Grup: " + groupService.getJumlahGrup());
        System.out.println("Total Aktivitas: " + activityService.getJumlahAktivitas());

        if (graphCore.getJumlahUser() > 0) {
            double rataRataTeman = (double)(graphCore.getJumlahPertemanan() * 2) / graphCore.getJumlahUser();
            System.out.println("Rata-rata Teman per User: " + String.format("%.1f", rataRataTeman));

            User userPopuler = friendService.cariUserPopuler();
            if (userPopuler != null) {
                System.out.println("User Terpopuler: " + userPopuler.getUsername() +
                        " (" + friendService.hitungDegree(userPopuler.getUsername()) + " teman)");
            }
        }
        System.out.println("========================");
    }

    public static void initializeDefaultUsers(GraphCore graphCore, ActivityService activityService) {
        System.out.println("Memuat data default...");

        // Tambah user default
        User[] defaultUsers = {
                new User("alice", "Alice Johnson", 25, "Jakarta"),
                new User("bob", "Bob Smith", 28, "Bandung"),
                new User("charlie", "Charlie Brown", 22, "Surabaya"),
                new User("diana", "Diana Prince", 30, "Yogyakarta"),
                new User("eve", "Eve Wilson", 26, "Medan")
        };

        for (User user : defaultUsers) {
            if (graphCore.tambahUser(user)) {
                activityService.buatAktivitas(user, "register", "Bergabung dengan jaringan sosial");
            }
        }

        // Tambah pertemanan default
        String[][] friendships = {
                {"alice", "bob"}, {"alice", "charlie"}, {"bob", "diana"},
                {"charlie", "eve"}, {"diana", "eve"}
        };

        for (String[] friendship : friendships) {
            if (graphCore.tambahPertemanan(friendship[0], friendship[1])) {
                User user1 = graphCore.getUserByUsername(friendship[0]);
                User user2 = graphCore.getUserByUsername(friendship[1]);
                if (user1 != null && user2 != null) {
                    activityService.buatAktivitas(user1, "add_friend", "Berteman dengan " + friendship[1], friendship[1]);
                    activityService.buatAktivitas(user2, "add_friend", "Berteman dengan " + friendship[0], friendship[0]);
                }
            }
        }

        System.out.println("✅ Data default berhasil dimuat!");
        System.out.println("Users: alice, bob, charlie, diana, eve");
        System.out.println("Silakan coba fitur-fitur yang tersedia!\n");
    }
}