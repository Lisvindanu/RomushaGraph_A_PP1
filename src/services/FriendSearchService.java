package services;

import entity.User;

public class FriendSearchService {
    private GraphCore graphCore;

    public FriendSearchService(GraphCore graphCore) {
        this.graphCore = graphCore;
    }

    //dapetin daftar teman dari seorang user
    public User[] getdaftarTeman(String username) {
        int index = graphCore.cariIndexUser(username);
        if(index == -1) {
            return null;
        }

        boolean[][] adjacencyMatrix = graphCore.getAdjacencyMatrix();
        int jumlahUser = graphCore.getJumlahUser();

        //hitung jumlah teman
        int jumlahTeman = 0;
        for (int i = 0; i<jumlahUser; i++) {
            if(adjacencyMatrix[index][i]) {
                jumlahTeman++;
            }
        }

        if(jumlahTeman == 0) {
            return new User[0];
        }

        //buat array teman
        User[] daftarTeman = new User[jumlahTeman];
        int temanIndex = 0;
        for(int i=0; i<jumlahTeman; i++) {
            daftarTeman[temanIndex] = graphCore.getUsersByIndex(i);
            temanIndex++;
        }
        return daftarTeman;
    }

    //cari teman bersama antara dua user
    public User[] cariTemanBersama(String username1, String username2) {
        int index1 = graphCore.cariIndexUser(username1);
        int index2 = graphCore.cariIndexUser(username2);

        if(index1 == -1 || index2 == -1) {
            return null;
        }

        boolean[][] adjacencyMatrix = graphCore.getAdjacencyMatrix();
        int jumlahUser = graphCore.getJumlahUser();

        User[] temanBersama = new User[jumlahUser];
        int jumlahTemanBersama = 0;

        for(int i=0; i<jumlahUser; i++) {
            if(adjacencyMatrix[index1][i] && adjacencyMatrix[index2][i]) {
                temanBersama[jumlahTemanBersama] = graphCore.getUsersByIndex(i);
                jumlahTemanBersama++;
            }
        }

        User[] hasil = new User[jumlahTemanBersama];
        for (int i = 0; i < jumlahTemanBersama; i++) {
            hasil[i] = temanBersama[i];
        }
        return hasil;
    }

    public User[] cariSaranTeman(String username) {
        int index = graphCore.cariIndexUser(username);
        if(index == -1) {
            return null;
        }

        boolean[][] adjacencyMatrix = graphCore.getAdjacencyMatrix();
        int jumlahUser = graphCore.getJumlahUser();

        boolean[] visited = new boolean[jumlahUser];
        User[] saran = new User[jumlahUser];
        int jumlahSaran = 0;

        // tandai diri sendiri dan teman lagnsung sebagai visiteed
        visited[index] = true;
        for(int i=0; i<jumlahUser; i++) {
            if(adjacencyMatrix[index][i]) {
                visited[i] = true;
            }
        }

        // cari teman dari teman (Level 2) - BFS implementasi
        for (int i = 0; i < jumlahUser; i++) {
            if(adjacencyMatrix[index][i]) {
                for (int j = 0; j < jumlahUser; j++) {
                    if(adjacencyMatrix[j][i] && !visited[j]) {
                        saran[j] = graphCore.getUsersByIndex(i);
                        jumlahSaran++;
                        visited[j] = true;
                    }
                }
            }
        }

        User[] hasil = new User[jumlahSaran];
        for (int i = 0; i < jumlahSaran; i++) {
            hasil[i] = saran[i];
        }
        return hasil;
    }

    //hitung jumlah koneksi dari seorang user
    public int hitungDegree(String username) {
        int index = graphCore.cariIndexUser(username);
        if(index == -1) {
            return 0;
        }

        boolean[][] adjacencyMatrix = graphCore.getAdjacencyMatrix();
        int jumlahUser = graphCore.getJumlahUser();
        int degree = 0;

        for (int i = 0; i < jumlahUser; i++) {
            if(adjacencyMatrix[index][i]) {
                degree++;
            }
        }
        return degree;
    }

    //cari user dengan teman terbanyak
    public User cariUserPopuler() {
        int maxTeman = 0;
        User userPopuler = null;
        int jumlahUser = graphCore.getJumlahUser();

        for (int i = 0; i < jumlahUser; i++) {
            User user = graphCore.getUsersByIndex(i);
            if(user != null) {
                int jumlahTeman = hitungDegree(user.getUsername());
                if(jumlahTeman > maxTeman) {
                    maxTeman = jumlahTeman;
                    userPopuler = user;
                }
            }
        }
        return userPopuler;
    }

}
