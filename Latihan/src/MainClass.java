
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        
        Scanner input = new Scanner (System.in);
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.println("Selamat Datang " + nama);
    }
}
