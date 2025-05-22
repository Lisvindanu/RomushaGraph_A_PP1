package services;

import entity.Pertemanan;
import entity.User;

public class GraphCore {
    private User[] users;
    private boolean[][] adjacencyMatrix;
    private Pertemanan[] daftarPertemanan;
    private int maxUsers, jumlahUser, jumlahPertemanan;

    public GraphCore() {
        this.maxUsers = 50;
        this.users = new User[maxUsers];
        this.adjacencyMatrix = new boolean[maxUsers][maxUsers];
        this.daftarPertemanan = new Pertemanan[maxUsers * maxUsers];
        this.jumlahUser = 0;
        this.jumlahPertemanan = 0;
    }

    public int cariIndexUser(String username) {
        for (int i = 0; i < maxUsers; i++) {
            if (users[i] != null && users[i].getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public boolean tambahUser(User user) {
        if(jumlahUser >= maxUsers) {
            System.out.println("Kapasitas penuh! tidak bisa menambah user lagi.");
            return false;
        }
        if (cariIndexUser(user.getUsername()) != -1) {
            System.out.println("Username '" + user.getUsername() + "' sudah digunakan!" );
            return false;
        }

        users[jumlahUser] = user;
        jumlahUser++;
        return true;
    }

    public boolean tambahPertemanan(String username1, String username2) {
        int index1 = cariIndexUser(username1);
        int index2 = cariIndexUser(username2);

        if(index1 == -1 || index2 == -1) {
            return false;
        }

        if(index1 == index2) {
            System.out.println("User tidak bisa berteman dengan dirinya sendiri !");
            return false;
        }

        if(adjacencyMatrix[index1][index2]) {
            System.out.println(username1 + " Dan " + username2 + " Sudah berteman !");
            return false;
        }

        //tambahkan peremanan (undirected)
        adjacencyMatrix[index1][index2] = true;
        adjacencyMatrix[index2][index1] = true;

        //buat object pertemanan
        Pertemanan pertemanan = new Pertemanan(users[index1], users[index2]);
        daftarPertemanan[jumlahPertemanan] = pertemanan;
        jumlahPertemanan++;

        //updte jumlah teman
        users[index1].setJumlahTeman(users[index1].getJumlahTeman() + 1);
        users[index2].setJumlahTeman(users[index2].getJumlahTeman() + 1);

        return true;
    }

    public boolean cekPertemanan(String username1, String username2) {
        int index1 = cariIndexUser(username1);
        int index2 = cariIndexUser(username2);

        if(index1 == -1 || index2 == -1) {
            return false;
        }

        return  adjacencyMatrix[index1][index2];
    }

    //getter

    public User[] getUsers() {
        return users;
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Pertemanan[] getDaftarPertemanan() {
        return daftarPertemanan;
    }

    public int getJumlahUser() {
        return jumlahUser;
    }

    public int getJumlahPertemanan() {
        return jumlahPertemanan;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    // get user by index
    public User getUsersByIndex(int index) {
        if(index >= 0 && index < jumlahUser) {
            return users[index];
        }
        return null;
    }

    public User getUserByUsername(String username) {
        int index = cariIndexUser(username);
        if(index != -1) {
            return users[index];
        }
        return null;
    }
}
