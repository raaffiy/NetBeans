package rumus;
import java.util.Scanner;

public class Rumus {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        do {
            System.out.println("\n =======================================\n ");
            System.out.println("-- Menu --");
            System.out.println("1. Rumus Segitiga");
            System.out.println("2. Rumus Persegi Panjang");
            System.out.println("3. Rumus Lingkaran");
            System.out.print("Masukkan Pilihan Anda (1 s.d 3): ");
            String pilih = scn.nextLine();
            System.out.println("\n =======================================\n");
            
            if (pilih.equals("1")) {
                System.out.print("Masukkan alas segitiga: ");
                double alas = scn.nextDouble();
                System.out.print("Masukkan tinggi segitiga: ");  
                double tinggi = scn.nextDouble();
                double luasSegitiga = 0.5 * alas * tinggi;
                System.out.println("\nLuas Segitiga: " + luasSegitiga);
                
            } else if (pilih.equals("2")) {
                System.out.print("Masukkan panjang persegi panjang: ");
                double panjang = scn.nextDouble();
                System.out.print("Masukkan lebar persegi panjang: ");
                double lebar = scn.nextDouble();
                double luasPersegiPanjang = panjang * lebar;
                System.out.println("\nLuas Persegi Panjang: " + luasPersegiPanjang);
                
            } else if (pilih.equals("3")){
                System.out.print("Masukkan jari-jari lingkaran: ");
                double jariJari = scn.nextDouble();
                double luasLingkaran = Math.PI * Math.pow(jariJari, 2);
                System.out.println("\nLuas Lingkaran: " + luasLingkaran);
                
            } else {
                System.out.println("Masukkan Dengan Benar !!");
            }
            
            System.out.print("\nApakah Anda Ingin Kembali Ke Menu [Y/T]: ");
            scn.nextLine(); // Membersihkan buffer keyboard
            String kembali = scn.nextLine();
            
            if (!kembali.equalsIgnoreCase("Y")) {
                break;
            }
        } while (true);
        
        System.out.println("\nTerima kasih telah menggunakan program ini.");
    }
}
