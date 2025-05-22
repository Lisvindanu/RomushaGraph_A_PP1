import services.ActivityService;
import services.FriendSearchService;
import services.GraphCore;
import services.GroupService;
import util.MenuView;
import util.SocialMediaUtil;

public class SocialMediaMain {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Sistem Jaringan Pertemanan Sosmed ");
        System.out.println("      Implementasi Graph Structure   ");
        System.out.println("=====================================");

        // inisialisasi servis
        GraphCore graphCore = new GraphCore();
        FriendSearchService friendService = new FriendSearchService(graphCore);
        GroupService groupService = new GroupService(graphCore);
        ActivityService activityService = new ActivityService();

        //panggil data default
        SocialMediaUtil.initializeDefaultUsers(graphCore, activityService);

        // main loop

        boolean berjalan = true;
        while (berjalan) {
            int pilihan = MenuView.displayMenuUtama();

            switch (pilihan) {
                case 1: SocialMediaUtil.tambahUserBaru(graphCore, activityService); break;
                case 2: SocialMediaUtil.tambahPertemanan(graphCore, activityService); break;
                case 3: SocialMediaUtil.lihatDaftarTeman(friendService, graphCore); break;
                case 4: SocialMediaUtil.cariTemanBersama(friendService, graphCore); break;
                case 5: SocialMediaUtil.cekStatusPertemanan(graphCore); break;
                case 6: SocialMediaUtil.tampilkanJaringan(graphCore); break;
                case 7: SocialMediaUtil.cariSaranTeman(friendService, graphCore); break;
                case 8: SocialMediaUtil.buatGrupBaru(groupService, graphCore, activityService); break;
                case 9: groupService.tampilkanSemuaGrup(); break;
                case 10:activityService.tampilkanAktivitasTerbaru(); break;
                case 11: SocialMediaUtil.tampilkanStatistik(graphCore, groupService, activityService, friendService); break;
                case 12:
                    berjalan = false;
                    System.out.println("\n🎉 Terimakasih telah menggunakan sistem jaringan pertemanan!");
                    System.out.println("gbye");
                    break;
                    default:
                        System.out.println("❌ Pilihan tidak valid. silahkan pilih menu 1-12.");
            }
        }
    }
}