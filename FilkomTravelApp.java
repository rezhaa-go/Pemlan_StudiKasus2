package Studi_Kasus2;

import java.util.*;
import java.time.LocalDate;

public class FilkomTravelApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan nama depan anda: ");
        String namaDepan = sc.nextLine();
        System.out.print("Masukkan nama belakang anda (tekan Enter jika tidak ada): ");
        String namaBelakang = sc.nextLine();
        System.out.print("Masukkan nomor HP: ");
        String HP = sc.nextLine();
        System.out.print("Apakah kamu member? (Ya/Tidak): ");
        String jawaban = sc.nextLine();
        boolean member = jawaban.equalsIgnoreCase("Ya");

        Pelanggan pel1;
        if (member) {
            System.out.print("Sejak kapan menjadi member (dd/mm/yyyy): ");
            String tanggal = sc.nextLine();
            pel1 = new Member(namaDepan, namaBelakang, HP, tanggal);
        } else {
            pel1 = new Guest(namaDepan, namaBelakang, HP);
        }

        System.out.println("Pilih Kendaraan:");
        System.out.println("1. Motor");
        System.out.println("2. Mobil");
        System.out.println("3. Bis");
        System.out.print("Pilihan: ");
        int pilihan = sc.nextInt();

        Kendaraan k1 = null;

        switch (pilihan) {
            case 1:
                k1 = new Motor();
                System.out.println("Motor telah dipilih");
                break;
            case 2:
                k1 = new Mobil();
                System.out.println("Mobil telah dipilih");
                break;
            case 3:
                k1 = new Bis();
                System.out.println("Bis telah dipilih");
                break;
            default:
                System.out.println("Pilih antara 1-3");
                return;
        }

        System.out.print("Masukkan banyak kendaraan: ");
        int jumlahKendaraan = sc.nextInt();
        System.out.print("Masukkan lama penyewaan (dalam jam): ");
        int lamaSewa = sc.nextInt();
        sc.nextLine();
        System.out.print("Tanggal mulai peminjaman: ");
        String awalPinjam = sc.nextLine();
        System.out.print("Tanggal selesai peminjaman: ");
        String selesaiPinjam = sc.nextLine();
        System.out.print("Masukkan jumlah penumpang: ");
        int penumpang = sc.nextInt();

        if (!(k1 instanceof Motor)) {
            int kursiTerisi = 0;
            while (kursiTerisi < penumpang) {
                k1.tampilkanKursi();
                System.out.println("Pilih kursi untuk penumpang ke-" + (kursiTerisi + 1));
                System.out.print("Baris: ");
                int b = sc.nextInt() - 1;
                System.out.print("Kolom: ");
                int k = sc.nextInt() - 1;

                if(k1 instanceof Bis && k == 2){
                    System.out.println("Kolom 3 pada Bis adalah lorong.");
                    continue;
                }

                if (k1.pilihKursi(b, k)) {
                    kursiTerisi++;
                }
            }
        }

        pel1.makeOrder(k1, lamaSewa, awalPinjam, selesaiPinjam, penumpang, jumlahKendaraan);
        Pemesanan p1 = pel1.getPemesananAktif();

        ArrayList<Promotion> daftarPromo = new ArrayList<>();
        daftarPromo.add(new PercentOffPromo("EJADISKON", 30, LocalDate.now().minusDays(1), LocalDate.now().plusDays(7)));
        daftarPromo.add(new CashbackPromo("EJACASHBACK", 10000, LocalDate.now().minusDays(1), LocalDate.now().plusDays(7)));
        daftarPromo.add(new FreeShippingPromo("EJAONGKIR", 5000, LocalDate.now().minusDays(1), LocalDate.now().plusDays(7)));

        System.out.print("Apakah Anda memiliki kode promo? (Ya/Tidak): ");
        String punyaPromo = sc.next();
        sc.nextLine();
        if (punyaPromo.equalsIgnoreCase("Ya")) {
            System.out.print("Masukkan kode promo: ");
            String inputKode = sc.nextLine();
            Promotion promoTerpilih = null;
            for (Promotion pr : daftarPromo) {
                if (pr.getKodePromo().equalsIgnoreCase(inputKode)) {
                    promoTerpilih = pr;
                    break;
                }
            }
            if (promoTerpilih != null) {
                p1.promotion(promoTerpilih);
            } else {
                System.out.println("Kode promo tidak ditemukan.");
            }
        }

        System.out.print("Ketik 'OK' untuk Checkout: ");
        String konfirmasi = sc.next();
        if(konfirmasi.equalsIgnoreCase("OK")) {
            p1.checkOut();
            System.out.println("------------------------------------");
            System.out.println("Pesanan Berhasil Dibuat!");
            System.out.println("Nomor Pesanan Anda: " + p1.getNoPesanan()); 
            System.out.println("Total yang harus dibayar: Rp" + p1.getTotalHarga());
            System.out.println("------------------------------------");

            System.out.println("======Konfirmasi Pembayaran=====");
            System.out.print("Masukkan id pesanan: ");
            int idPesanan = sc.nextInt();

            pel1.confirmPay(idPesanan);

            if (p1.getStatus().name().equalsIgnoreCase("SUCCESSFULL")) {
                System.out.println("Pembayaran berhasil!");
                System.out.println("\n======Struk Pemesanan======");
                p1.display();
            } else {
                System.out.println("Pembayaran gagal. Struk tidak dapat dicetak.");
            }
        } else {
            System.out.println("Checkout dibatalkan. Struk tidak dapat dicetak.");
        }
    }
}