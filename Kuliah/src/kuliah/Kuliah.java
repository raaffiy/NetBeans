package kuliah;

import java.util.Scanner; // Mengimpor kelas Scanner dari paket java.util

public class Kuliah {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String jurusan, kls = null;
        int harga = 0;
        
        System.out.println("1. Jurusan Ekonomi - Kelas Pagi");
        System.out.println("2. Jurusan Ekonomi - Kelas Malam");
        System.out.println("3. Jurusan Sastra Indonesia - Kelas Pagi");
        System.out.println("4. Jurusan Sastra Indonesia - Kelas Malam");
        System.out.print("Masukkan kode : ");
        int pilih = input.nextInt();
        
        if (pilih == 1) {
            jurusan = "Ekonomi";
            kls = "Pagi";
            harga = 1750000;
        } else if (pilih == 2) {
            jurusan = "Ekonomi";
            kls = "Malam";
            harga = 2000000;
        } else if (pilih == 3) {
            jurusan = "Sastra Indonesia";
            kls = "Pagi";
            harga = 1900000;
        } else if (pilih == 4) {
            jurusan = "Sastra Indonesia";
            kls = "Malam";
            harga = 2300000;
        } else {
            jurusan = "Data Tidak ada";
            harga = 0;
        }
        
        System.out.println("----------------------------------");
        System.out.println("Jurusan Kuliah : " + jurusan);
        System.out.println("Kelas Kuliah : " + kls);
        System.out.println("Biaya Kuliah : " + harga);
    }
}
