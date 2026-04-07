package Studi_Kasus2;

import java.time.LocalDate;
import java.util.Random;

public class Pemesanan {
    Pelanggan pelanggan;
    Kendaraan kendaraan;
    StatusPesanan statusPesanan;
    private int lamaPenyewaan;
    private String tanggalMulai;
    private String tanggalSelesai;
    private int jumlahPenumpang;
    private int jumlahKendaraan;
    LocalDate tanggalPesanan;
    private int noPesanan;
    private boolean isCheckedOut = false;
    private double ongkosKirim;
    private double totalHarga;
    private double hargaDiskon;
    private double potonganPromo;
    private double potonganOngkir;
    private double cashbackDidapat;

    private static Random rand = new Random();

    public Pemesanan(Pelanggan pelanggan, Kendaraan kendaraan, int lamaPenyewaan, String tanggalMulai, String tanggalSelesai, int jumlahPenumpang, int jumlahKendaraan) {
        this.pelanggan = pelanggan;
        this.kendaraan = kendaraan;
        this.statusPesanan = StatusPesanan.UNPAID;
        this.lamaPenyewaan = lamaPenyewaan;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.jumlahPenumpang = jumlahPenumpang;
        this.jumlahKendaraan = jumlahKendaraan;
        this.noPesanan = rand.nextInt(900000) + 100000;
        hitungTotal();
        ongkir();
    }

    public void checkOut() {
        this.tanggalPesanan = LocalDate.now();
        this.isCheckedOut = true;
    }

    public void pay() {
        if (this.isCheckedOut) {
            this.statusPesanan = StatusPesanan.SUCCESSFULL;
            System.out.println("Pembayaran berhasil!");
        } else {
            System.out.println("Maaf, Anda belum melakukan checkout. Silakan checkout terlebih dahulu sebelum melakukan pembayaran.");
        }
    }

    public void setStatus(StatusPesanan statusBaru) {
        this.statusPesanan = statusBaru;
    }

    public void promotion(Promotion promo) {
        try {
            if (promo != null) {
                if (promo.isPromoValid() && promo.isCustomerEligible(this.pelanggan) && promo.isMinimumPriceEligible(this)) {
                    this.potonganPromo = promo.hitungPotongan(this.totalHarga);
                    this.potonganOngkir = promo.hitungPotonganOngkir(this.ongkosKirim);
                    this.cashbackDidapat = promo.hitungCashback(this.totalHarga);
                    System.out.println("Promo " + promo.getKodePromo() + " berhasil diterapkan!");
                } else {
                    System.out.println("Anda tidak memenuhi syarat untuk promo ini.");
                }
            } else {
                System.out.println("Kode promo tidak valid.");
            }
        } catch (PromoException e) {
            System.out.println("Gagal menerapkan promo: " + e.getMessage());
            this.potonganPromo = 0;
            this.potonganOngkir = 0;
            this.cashbackDidapat = 0;
        }
    }

    public StatusPesanan getStatus() {
        return this.statusPesanan;
    }

    public int getLamaPenyewaan() {
        return lamaPenyewaan;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void hitungTotal() {
        this.totalHarga = kendaraan.hargaSewa(lamaPenyewaan) * jumlahKendaraan;
        this.hargaDiskon = pelanggan.diskon(totalHarga);
    }

    public void ongkir() {
        this.ongkosKirim = kendaraan.ongkosKirim() * jumlahKendaraan;
    }

    public double getBiayaOngkir() {
        return ongkosKirim;
    }

    public LocalDate getTanggalPesanan() {
        return tanggalPesanan;
    }

    public int getNoPesanan() {
        return noPesanan;
    }

    public void allert() {
        if (jumlahPenumpang > (kendaraan.maksKapasitas * jumlahKendaraan)) {
            System.out.println("Anda telah melewati batas maksimum! Tolong perhatikan batas maksimum penumpang!");
        }
    }

    public double getTotalHarga() {
        double totalH = (totalHarga - hargaDiskon - potonganPromo);
        double totalO = (ongkosKirim - potonganOngkir);
        return Math.max(0, totalH) + Math.max(0, totalO);
    }

    public void display() {
        if (!isCheckedOut) {
            System.out.println("Maaf, pesanan belum di-checkout. Silakan checkout terlebih dahulu untuk melihat detail pesanan.");
            return;
        }

        System.out.println("Nomor Pesanan       : " + noPesanan);
        pelanggan.display();
        System.out.println("==============================");
        System.out.println("Tipe                : " + kendaraan.tipeKendaraan);
        System.out.println("Harga/jam           : " + kendaraan.hargaKendaraan);
        System.out.println("Maksimum Kapasitas  : " + kendaraan.maksKapasitas);
        if (!(kendaraan instanceof Motor)) {
            System.out.println("Kursi Dipesan       : " + kendaraan.getDaftarKursi());
        }
        System.out.println("==============================");
        System.out.println("Tanggal Pemesanan   : " + tanggalPesanan);
        System.out.println("Jumlah Kendaraan    : " + jumlahKendaraan);
        System.out.println("Jumlah Penumpang    : " + jumlahPenumpang);
        System.out.println("Lama Penyewaan      : " + lamaPenyewaan + " jam");
        System.out.println("Mulai Peminjaman    : " + tanggalMulai);
        System.out.println("Selesai Peminjaman  : " + tanggalSelesai);
        System.out.println("------------------------------");
        System.out.println("Diskon Member       : " + hargaDiskon);
        System.out.println("Potongan Promo      : " + potonganPromo);
        System.out.println("Potongan Ongkir     : " + potonganOngkir);
        System.out.println("Cashback didapat    : " + cashbackDidapat);
        System.out.println("Biaya Pengiriman    : " + (ongkosKirim - potonganOngkir));
        System.out.println("Total Harga         : " + getTotalHarga());
        allert();
        System.out.println("==============================");
    }
}