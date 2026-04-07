package Studi_Kasus2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PercentOffPromo extends Promotion {
    public PercentOffPromo(String kodePromo, double jumlahDiskon, LocalDate tanggalMulai, LocalDate tanggalBerakhir) {
        super(kodePromo, jumlahDiskon, tanggalMulai, tanggalBerakhir);
    }

    @Override
    public double getBesarPromo(Pemesanan pemesanan) {
        return (pemesanan.getTotalHarga() * (jumlahDiskon / 100));
    }

    @Override
public boolean isCustomerEligible(Pelanggan pelanggan) {
    if (pelanggan instanceof Member) {
        Member m = (Member) pelanggan;
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate tanggalBergabung = LocalDate.parse(m.getKapanMember().trim(), formatter);
            long selisihHari = java.time.temporal.ChronoUnit.DAYS.between(tanggalBergabung, LocalDate.now());
            
            return selisihHari > 30;
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Peringatan: Format tanggal member di data pelanggan tidak valid (Gunakan dd/mm/yyyy).");
            return false;
        }
    }
    return false;
}

    @Override
    public boolean isMinimumPriceEligible(Pemesanan pemesanan){
        return pemesanan.getTotalHarga() > 50000;
    }

    @Override
    public boolean isShippingFeeEligible(Pemesanan pemesanan) {
        return false;
    }

    @Override
    public double hitungPotongan(double totalHarga) throws PromoException {
        double hasilPotongan = totalHarga * (jumlahDiskon / 100);
        if (hasilPotongan > totalHarga) {
            throw new PromoException("Potongan tidak boleh melebihi total harga.");
        }
        return hasilPotongan;
    }
}